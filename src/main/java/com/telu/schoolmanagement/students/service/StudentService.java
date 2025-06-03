package com.telu.schoolmanagement.students.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.telu.schoolmanagement.common.constant.AppConstant;
import com.telu.schoolmanagement.common.redis.RedisCacheUtil;
import com.telu.schoolmanagement.program.model.Programs;
import com.telu.schoolmanagement.program.repository.ProgramRepository;
import com.telu.schoolmanagement.students.dto.StudentRequestDTO;
import com.telu.schoolmanagement.students.dto.StudentResponseDTO;
import com.telu.schoolmanagement.students.mapper.StudentMapper;
import com.telu.schoolmanagement.students.model.Students;
import com.telu.schoolmanagement.students.repository.StudentRepository;
import com.telu.schoolmanagement.users.model.Users;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Transactional
    public List<StudentResponseDTO> getAllStudents(){
        List<StudentResponseDTO> redisData = redisCacheUtil.getCachedList(
                AppConstant.REDIS_GETALL_STUDENT,
                new TypeReference<List<StudentResponseDTO>>() {}
        );

        if (redisData != null) return redisData;

        List<StudentResponseDTO> result = studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDto)
                .toList();

        redisCacheUtil.cacheValue(AppConstant.REDIS_GETALL_STUDENT, result);
        return result;
    }

    @Transactional
    public List<StudentResponseDTO> searchStudentsByNim(String nim) {
        return studentRepository.findByNimIgnoreCaseContaining(nim)
                .stream()
                .map(StudentMapper::toDto)
                .toList();
    }

    @Transactional
    public void createStudent(StudentRequestDTO requestDTO, Long userId) {
        deleteAllStudentCache();

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

        Programs program = programRepository.findById(requestDTO.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Program with ID " + requestDTO.getProgramId() + " not found"));

        Students student = Students.builder()
                .users(user)
                .nim(requestDTO.getNim())
                .semester(requestDTO.getSemester())
                .programs(program)
                .build();

        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(String nim, StudentRequestDTO requestDTO) {
        deleteStudentCache(nim);

        Students student = studentRepository.findByNim(nim)
                .orElseThrow(() -> new EntityNotFoundException("Student with NIM " + nim + " not found"));

        if (requestDTO.getProgramId() != null) {
            Programs program = programRepository.findById(requestDTO.getProgramId())
                    .orElseThrow(() -> new EntityNotFoundException("Program with ID " + requestDTO.getProgramId() + " not found"));
            student.setPrograms(program);
        }

        if (requestDTO.getNim() != null) {
            student.setNim(requestDTO.getNim());
        }

        if (requestDTO.getSemester() != null) {
            student.setSemester(requestDTO.getSemester());
        }

        studentRepository.save(student);
    }

    @Transactional
    public void deleteStudentByNim(String nim) {
        deleteStudentCache(nim);
        Students student = studentRepository.findByNim(nim)
                .orElseThrow(() -> new EntityNotFoundException("Student with NIM " + nim + " not found"));

        studentRepository.delete(student);
    }

    private void deleteAllStudentCache() {
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETALL_STUDENT);
    }

    private void deleteStudentCache(String nim) {
        deleteAllStudentCache();
        redisCacheUtil.deleteCache("student::" + nim);
    }
}
