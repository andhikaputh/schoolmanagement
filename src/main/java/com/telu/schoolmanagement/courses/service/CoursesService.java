package com.telu.schoolmanagement.courses.service;

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

    public List<CoursesResponseDTO> getAllCourses() {
        return coursesRepository.findAll()
                .stream()
                .map(CoursesMapper::toDTO)
                .toList();
    }

    public CoursesResponseDTO getCourseById(Long id) {
        return coursesRepository.findById(id)
                .map(CoursesMapper::toDTO)
                .orElse(null);
    }

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
        if (!coursesRepository.existsById(id)){
            throw new EntityNotFoundException("Can't find course with ID "+id);
        }
        coursesRepository.deleteById(id);
    }
}
