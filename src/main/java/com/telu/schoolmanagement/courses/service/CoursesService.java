package com.telu.schoolmanagement.courses.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.telu.schoolmanagement.common.constant.AppConstant;
import com.telu.schoolmanagement.common.redis.RedisCacheUtil;
import com.telu.schoolmanagement.courses.dto.CoursesRequestDTO;
import com.telu.schoolmanagement.courses.dto.CoursesResponseDTO;
import com.telu.schoolmanagement.courses.mapper.CoursesMapper;
import com.telu.schoolmanagement.courses.model.Courses;
import com.telu.schoolmanagement.courses.repository.CoursesRepository;
import com.telu.schoolmanagement.program.model.Programs;
import com.telu.schoolmanagement.program.repository.ProgramRepository;
import com.telu.schoolmanagement.users.model.Users;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    public List<CoursesResponseDTO> getAllCourses() {
        List<CoursesResponseDTO> cachedCourses = redisCacheUtil.getCachedList(
                AppConstant.REDIS_GETALL_COURSE,
                new TypeReference<List<CoursesResponseDTO>>() {}
        );

        if (cachedCourses != null) {
            return cachedCourses;
        }

        List<CoursesResponseDTO> courses = coursesRepository.findAll()
                .stream()
                .map(CoursesMapper::toDTO)
                .toList();

        redisCacheUtil.cacheValue(
                AppConstant.REDIS_GETALL_COURSE,
                courses
        );

        return courses;
    }

    public CoursesResponseDTO getCourseById(Long id) {
        CoursesResponseDTO cachedCourse = redisCacheUtil.getCachedValue(
                AppConstant.REDIS_GETCOURSE_BY_ID+ id,
                CoursesResponseDTO.class
        );

        if (cachedCourse != null) {
            return cachedCourse;
        }

        CoursesResponseDTO course = coursesRepository.findById(id)
                .map(CoursesMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Can't find course with ID "+id));

        redisCacheUtil.cacheValue(
                AppConstant.REDIS_GETCOURSE_BY_ID + id,
                course
        );

        return course;
    }

    //TODO: Research if this is the best practice for searching
    public List<CoursesResponseDTO> searchCourses(Long id, String name) {
        if (id != null && name == null) {
            return coursesRepository.findById(id)
                    .stream()
                    .map(CoursesMapper::toDTO)
                    .toList();
        } else if (name != null && id == null) {
            return coursesRepository.findByNameIgnoreCaseContaining(name)
                    .stream()
                    .map(CoursesMapper::toDTO)
                    .toList();
        }
        return null;
    }

    public void createCourse(CoursesRequestDTO request){
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETALL_COURSE);

        Programs program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find program with ID "+request.getProgramId()));

        Users user = usersRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new EntityNotFoundException("Can't find user with ID "+request.getCreatedBy()));

        Courses courses = Courses.builder()
                .name(request.getName())
                .sks(request.getSks())
                .programs(program)
                .createdBy(user)
                .updatedBy(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        coursesRepository.save(courses);
    }

    public void updateCourse(Long id, CoursesRequestDTO request){
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETALL_COURSE);
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETCOURSE_BY_ID + id);

        Courses course = coursesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find course with ID "+id));

        Programs program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find program with ID "+request.getProgramId()));

        Users user = usersRepository.findById(request.getUpdatedBy())
                .orElseThrow(() -> new EntityNotFoundException("Can't find user with ID "+request.getUpdatedBy()));

        course.setName(request.getName());
        course.setSks(request.getSks());
        course.setPrograms(program);    // To-be confirmed
        course.setUpdatedBy(user);    // To-be confirmed
        course.setUpdatedAt(LocalDateTime.now());       // To-be confirmed

        coursesRepository.save(course);
    }

    public void deleteCourse(Long id){
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETALL_COURSE);
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETCOURSE_BY_ID + id);

        if (!coursesRepository.existsById(id)){
            throw new EntityNotFoundException("Can't find course with ID "+id);
        }
        coursesRepository.deleteById(id);
    }
}
