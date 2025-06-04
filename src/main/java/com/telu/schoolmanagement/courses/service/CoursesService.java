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
import jakarta.transaction.Transactional;
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

    @Transactional
    public List<CoursesResponseDTO> getAllCourses() {
        List<CoursesResponseDTO> cachedCourses = redisCacheUtil.getCachedList(
                AppConstant.REDIS_GET_ALL_COURSE,
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
                AppConstant.REDIS_GET_ALL_COURSE,
                courses
        );

        return courses;
    }

    @Transactional
    public CoursesResponseDTO getCourseById(Long id) {
        CoursesResponseDTO cachedCourse = redisCacheUtil.getCachedValue(
                AppConstant.REDIS_GETCOURSE + id,
                CoursesResponseDTO.class
        );

        if (cachedCourse != null) {
            return cachedCourse;
        }

        CoursesResponseDTO course = coursesRepository.findById(id)
                .map(CoursesMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Can't find course with ID "+id));

        redisCacheUtil.cacheValue(
                AppConstant.REDIS_GETCOURSE + id,
                course
        );

        return course;
    }

    //TODO handle search by id and name, if both provided, return null or throw exception
    //TODO cache handling for searchCourses
    @Transactional
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

    @Transactional
    public void createCourse(CoursesRequestDTO request){
        redisCacheUtil.deleteCache(AppConstant.REDIS_GET_ALL_COURSE);

        Programs program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find program with ID "+request.getProgramId()));

        Users user = usersRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new EntityNotFoundException("Can't find user with ID "+request.getCreatedBy()));

        Courses courses = Courses.builder()
                .name(request.getName())
                .code(request.getCode())
                .semester(request.getSemester())
                .credit(request.getCredit())
                .programs(program)
                .createdBy(user)
                .createdAt(LocalDateTime.now())
                .build();

        coursesRepository.save(courses);
    }

    @Transactional
    public void updateCourse(Long id, CoursesRequestDTO request){
        redisCacheUtil.deleteCache(AppConstant.REDIS_GET_ALL_COURSE);
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETCOURSE + id);

        Courses course = coursesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find course with ID "+id));

        Programs program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find program with ID "+request.getProgramId()));

        Users user = usersRepository.findById(request.getUpdatedBy())
                .orElseThrow(() -> new EntityNotFoundException("Can't find user with ID "+request.getUpdatedBy()));

        course.setName(request.getName());
        course.setCode(request.getCode());
        course.setSemester(request.getSemester());
        course.setCredit(request.getCredit());
        course.setPrograms(program);
        course.setUpdatedBy(user);
        course.setUpdatedAt(LocalDateTime.now());

        coursesRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Long id){
        redisCacheUtil.deleteCache(AppConstant.REDIS_GET_ALL_COURSE);
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETCOURSE + id);

        if (!coursesRepository.existsById(id)){
            throw new EntityNotFoundException("Can't find course with ID "+id);
        }
        coursesRepository.deleteById(id);
    }
}
