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
                .faculty(faculty)
                .build();

        lecturersRepository.save(lecturer);
    }

    public void deleteAllLecturerCache(){
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETALL_LECTURE);
    }
    private void deleteLecturerCache(String nidn) {
        deleteAllLecturerCache();
        redisCacheUtil.deleteCache("lecturer::" + nidn);
    }

    public void updateLecturer(String nidn, LecturersRequestDTO requestDTO) {
        deleteLecturerCache(nidn);

        Lecturers lecturer = lecturersRepository.findByNidn(nidn)
                .orElseThrow(() -> new EntityNotFoundException("Lecturer with NIDN " + nidn + " not found"));

        if (requestDTO.getFacultyId() != null) {
            Faculties faculty = facultyRepository.findById(requestDTO.getFacultyId())
                    .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));
            lecturer.setFaculty(faculty);
        }

        lecturersRepository.save(lecturer);

    }
    public void deleteLecturerByNidn(String nidn) {
        deleteLecturerCache(nidn);
        Lecturers lecturer = lecturersRepository.findByNidn(nidn)
                .orElseThrow(() -> new EntityNotFoundException("Lecturer with NIDN " + nidn + " not found"));

        lecturersRepository.delete(lecturer);
    }
}
