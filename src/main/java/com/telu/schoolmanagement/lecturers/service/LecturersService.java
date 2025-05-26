package com.telu.schoolmanagement.lecturers.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.telu.schoolmanagement.common.constant.AppConstant;
import com.telu.schoolmanagement.common.redis.RedisCacheUtil;
import com.telu.schoolmanagement.faculty.dto.FacultyResponseDTO;
import com.telu.schoolmanagement.faculty.mapper.FacultyMapper;
import com.telu.schoolmanagement.faculty.model.Faculties;
import com.telu.schoolmanagement.faculty.repository.FacultyRepository;
import com.telu.schoolmanagement.lecturers.dto.LecturersRequestDTO;
import com.telu.schoolmanagement.lecturers.dto.LecturersResponseDTO;
import com.telu.schoolmanagement.lecturers.mapper.LecturersMapper;
import com.telu.schoolmanagement.lecturers.model.Lecturers;
import com.telu.schoolmanagement.lecturers.repository.LecturersRepository;
import com.telu.schoolmanagement.users.model.Users;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LecturersService {
    @Autowired
    private LecturersRepository lecturersRepository;


    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RedisCacheUtil redisCacheUtil;

    public List<LecturersResponseDTO> getAllLecturers(){
        List<LecturersResponseDTO> redisData = redisCacheUtil.getCachedList(
                AppConstant.REDIS_GETALL_LECTURE,
                new TypeReference<List<LecturersResponseDTO>>() {}
        );
        System.out.println("Lecture from redis "+ redisData);

        if (redisData != null) return redisData;

        System.out.println("Call lecturer data from DB");
        List<LecturersResponseDTO> result = lecturersRepository.findAll()
                .stream()
                .map(LecturersMapper::toDTO)
                .toList();

        redisCacheUtil.cacheValue(AppConstant.REDIS_GETALL_LECTURE,result);
        return result;
    }

    public LecturersResponseDTO getLecturerById(Long id) {
        String redisKey = "lecturer::" + id;
        LecturersResponseDTO redisData = redisCacheUtil.getCachedValue(
                redisKey,
                LecturersResponseDTO.class
        );

        if (redisData != null) return redisData;

        System.out.println("Call data lecture from DB");
        LecturersResponseDTO result = LecturersMapper.toDTO(lecturersRepository.findById(id).orElseThrow());
        redisCacheUtil.cacheValue(redisKey,result);
        return result;
    }
    public List<LecturersResponseDTO> searchLecturersByNidn(String nidn) {
        return lecturersRepository.findByNidnIgnoreCaseContaining(nidn)
                .stream()
                .map(LecturersMapper::toDTO)
                .toList();
    }
    public void createLecturer(LecturersRequestDTO requestDTO, Long userId) {
        deleteAllLecturerCache();

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

        Faculties faculty = facultyRepository.findById(requestDTO.getFacultyId())
                .orElseThrow(() -> new EntityNotFoundException("Faculty with ID " + requestDTO.getFacultyId() + " not found"));

        Lecturers lecturer = Lecturers.builder()
                .user(user)
                .nidn(requestDTO.getNidn())
                .faculty(faculty)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        lecturersRepository.save(lecturer);
    }

    public void deleteAllLecturerCache(){
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETALL_LECTURE);
    }
    private void deleteLecturerCache(Long id) {
        deleteAllLecturerCache();
        redisCacheUtil.deleteCache("lecturer::" + id);
    }

    public void updateLecturer(Long id, LecturersRequestDTO requestDTO) {
        deleteLecturerCache(id);

        Lecturers lecturer = lecturersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lecturer with ID " + id + " not found"));

        if (requestDTO.getFacultyId() != null) {
            Faculties faculty = facultyRepository.findById(requestDTO.getFacultyId())
                    .orElseThrow(() -> new EntityNotFoundException("Faculty with ID " + requestDTO.getFacultyId() + " not found"));
            lecturer.setFaculty(faculty);
        }

        if (requestDTO.getNidn() != null) {
            lecturer.setNidn(requestDTO.getNidn());
        }

        lecturer.setUpdatedAt(LocalDateTime.now());
        lecturersRepository.save(lecturer);
    }
    public void deleteLecturer(Long id) {
        deleteLecturerCache(id);

        if (!lecturersRepository.existsById(id)) {
            throw new EntityNotFoundException("Lecturer with ID " + id + " not found");
        }
        lecturersRepository.deleteById(id);
    }
}
