package com.telu.schoolmanagement.course_registrations.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.telu.schoolmanagement.common.constant.AppConstant;
import com.telu.schoolmanagement.common.redis.RedisCacheUtil;
import com.telu.schoolmanagement.course_registrations.dto.CourseRegistrationsRequestDTO;
import com.telu.schoolmanagement.course_registrations.dto.CourseRegistrationsResponseDTO;
import com.telu.schoolmanagement.course_registrations.mapper.CourseRegistrationsMapper;
import com.telu.schoolmanagement.course_registrations.model.CourseRegistrations;
import com.telu.schoolmanagement.course_registrations.repository.CourseRegistrationsRepository;
import com.telu.schoolmanagement.students.model.Students;
import com.telu.schoolmanagement.students.repository.StudentRepository;
import com.telu.schoolmanagement.users.model.Users;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseRegistrationsService {

    @Autowired
    private CourseRegistrationsRepository courseRegistrationsRepository;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Transactional
    public List<CourseRegistrationsResponseDTO> getAllCourseRegistrations() {
        List<CourseRegistrationsResponseDTO> cachedCourseRegistrations = redisCacheUtil.getCachedList(
                AppConstant.REDIS_GET_ALL_COURSEREGISTRATION,
                new TypeReference<List<CourseRegistrationsResponseDTO>>() {}
        );

        if ( cachedCourseRegistrations != null) {
            return cachedCourseRegistrations;
        }

        List<CourseRegistrationsResponseDTO> courseRegistrations =
                courseRegistrationsRepository.findAll()
                .stream()
                .map(CourseRegistrationsMapper::toDTO)
                .toList();

        redisCacheUtil.cacheValue(
                AppConstant.REDIS_GET_ALL_COURSEREGISTRATION,
                courseRegistrations
        );

        return courseRegistrations;
    }

    @Transactional
    public CourseRegistrationsResponseDTO getCourseRegistrationById(Long id) {
        CourseRegistrationsResponseDTO cachedCourseRegistration = redisCacheUtil.getCachedValue(
                AppConstant.REDIS_GETCOURSEREGISTRATION + id,
                CourseRegistrationsResponseDTO.class
        );

        if (cachedCourseRegistration != null) {
            return cachedCourseRegistration;
        }

        CourseRegistrationsResponseDTO courseRegistration =
                courseRegistrationsRepository.findById(id)
                .map(CourseRegistrationsMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Course Registration not found with id: " + id));

        redisCacheUtil.cacheValue(
                AppConstant.REDIS_GETCOURSEREGISTRATION + id,
                courseRegistration
        );

        return courseRegistration;
    }

    @Transactional
    public void createCourseRegistration(CourseRegistrationsRequestDTO request) {
        Students student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + request.getStudentId()));

        Users user = userRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.getCreatedBy()));

        CourseRegistrations courseRegistration = CourseRegistrations.builder()
                .student(student)
                .courseAssignmentId(request.getCourseAssignmentId())
                .academicYear(request.getAcademicYear())
                .createdBy(user)
                .createdAt(LocalDateTime.now())
                .build();

        courseRegistrationsRepository.save(courseRegistration);

        redisCacheUtil.deleteCache(AppConstant.REDIS_GET_ALL_COURSEREGISTRATION);
    }

    @Transactional
    public void updateCourseRegistration(Long id, CourseRegistrationsRequestDTO request) {
        CourseRegistrations courseRegistration = courseRegistrationsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course Registration not found with id: " + id));

        Students student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + request.getStudentId()));

        Users user = userRepository.findById(request.getUpdatedBy())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.getUpdatedBy()));

        courseRegistration.setStudent(student);
        courseRegistration.setCourseAssignmentId(request.getCourseAssignmentId());
        courseRegistration.setAcademicYear(request.getAcademicYear());
        courseRegistration.setUpdatedBy(user);
        courseRegistration.setUpdatedAt(LocalDateTime.now());

        courseRegistrationsRepository.save(courseRegistration);

        redisCacheUtil.deleteCache(AppConstant.REDIS_GET_ALL_COURSEREGISTRATION);
    }

    public void deleteCourseRegistration(Long id) {
        CourseRegistrations courseRegistration = courseRegistrationsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course Registration not found with id: " + id));

        courseRegistrationsRepository.delete(courseRegistration);

        redisCacheUtil.deleteCache(AppConstant.REDIS_GET_ALL_COURSEREGISTRATION);
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETCOURSEREGISTRATION + id);
    }
}
