package com.telu.schoolmanagement.faculty.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.telu.schoolmanagement.common.constant.AppConstant;
import com.telu.schoolmanagement.common.redis.RedisCacheUtil;
import com.telu.schoolmanagement.faculty.dto.FacultyRequestDTO;
import com.telu.schoolmanagement.faculty.dto.FacultyResponseDTO;
import com.telu.schoolmanagement.faculty.mapper.FacultyMapper;
import com.telu.schoolmanagement.faculty.model.Faculties;
import com.telu.schoolmanagement.faculty.repository.FacultyRepository;
import com.telu.schoolmanagement.users.model.Users;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    RedisCacheUtil redisCacheUtil;

    @Autowired
    private UsersRepository usersRepository;

    public List<FacultyResponseDTO> getAllFaculty(){
        List<FacultyResponseDTO> redisData = redisCacheUtil.getCachedList(
                AppConstant.REDIS_GETALL_FACULTY,
                new TypeReference<List<FacultyResponseDTO>>() {}
        );
        System.out.println("Faculty from redis "+ redisData);

        if (redisData != null) return redisData;

        System.out.println("Call data from DB");
        var result = facultyRepository.findAll().stream()
                .map(FacultyMapper::toDTO)
                .toList();

        redisCacheUtil.cacheValue(AppConstant.REDIS_GETALL_FACULTY,result);
        return result;
    }

    public FacultyResponseDTO getFacultyById(Long id){
       String redisKey = "faculty::"+id;
       FacultyResponseDTO redisData = redisCacheUtil.getCachedValue(
               AppConstant.REDIS_GETALL_FACULTY,
               FacultyResponseDTO.class
       );

       if(redisData !=null) return redisData;

        System.out.println("call data from DB");
        FacultyResponseDTO result = FacultyMapper.toDTO(facultyRepository.findById(id).orElseThrow());
        redisCacheUtil.cacheValue(redisKey,result);

        return result;
    }

    public List<FacultyResponseDTO> getFacultyByName(String query){
        return facultyRepository.findByNameIgnoreCaseContaining(query).stream()
                .map(FacultyMapper::toDTO)
                .toList();
    }

    public void createFaculty(FacultyRequestDTO requestDTO, Long userId) {
        deleteAllFacultyCache();

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found."));

        if (user.getRole() == null || user.getRole().getId() != 1 ) {
            throw new SecurityException("Only user admin that can create faculty.");
        }

        Faculties faculty = Faculties.builder()
                .name(requestDTO.getFacultyName())
                .createdBy(user)
                .updatedBy(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        facultyRepository.save(faculty);
    }

    public void deleteFacultyById(Long id) {
        deleteAllFacultyIDCache(id);

        if (!facultyRepository.existsById(id)) {
            throw new EntityNotFoundException("Faculty with id " + id + " not found.");
        }
        facultyRepository.deleteById(id);
    }

    public void updateFaculty(Long id, FacultyRequestDTO requestDTO) {
        deleteAllFacultyIDCache(id);

        Faculties newFaculty = facultyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Faculty with ID " + id + " not found."));

        newFaculty.setName(requestDTO.getFacultyName());
        newFaculty.setUpdatedAt(LocalDateTime.now());
        facultyRepository.save(newFaculty);
    }

    public void deleteAllFacultyCache(){
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETALL_FACULTY);
    }

    public void deleteAllFacultyIDCache(Long id){
        deleteAllFacultyCache();
        redisCacheUtil.deleteCache("jurusan::"+id);
    }
}
