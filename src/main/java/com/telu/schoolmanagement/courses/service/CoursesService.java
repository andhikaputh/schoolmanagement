package com.telu.schoolmanagement.courses.service;

import com.telu.schoolmanagement.courses.dto.CoursesRequestDTO;
import com.telu.schoolmanagement.courses.dto.CoursesResponseDTO;
import com.telu.schoolmanagement.courses.mapper.CoursesMapper;
import com.telu.schoolmanagement.courses.model.Courses;
import com.telu.schoolmanagement.courses.repository.CoursesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

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
        Courses courses = Courses.builder()
                .name(request.getName())
                .sks(request.getSks())
                .programId(request.getProgramId())  // To-be confirmed
                .createdBy(request.getCreatedBy())  // To-be confirmed
                .updatedBy(request.getCreatedBy())  // To-be confirmed
                .createdAt(LocalDateTime.now())     // To-be confirmed
                .updatedAt(LocalDateTime.now())     // To-be confirmed
                .build();

        coursesRepository.save(courses);
    }

    public void updateCourse(Long id, CoursesRequestDTO request){
        Courses course = coursesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find course with ID "+id));

        //TODO update when programs module has developed
        course.setName(request.getName());
        course.setSks(request.getSks());
        course.setProgramId(request.getProgramId());    // To-be confirmed
        course.setUpdatedBy(request.getUpdatedBy());    // To-be confirmed
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
