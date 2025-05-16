package com.telu.schoolmanagement.courses.service;

import com.telu.schoolmanagement.courses.dto.CoursesResponseDTO;
import com.telu.schoolmanagement.courses.mapper.CoursesMapper;
import com.telu.schoolmanagement.courses.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (id != null) {
            return coursesRepository.findById(id)
                    .stream()
                    .map(CoursesMapper::toDTO)
                    .toList();
        } else if (name != null) {
            return coursesRepository.findByNameIgnoreCaseContaining(name)
                    .stream()
                    .map(CoursesMapper::toDTO)
                    .toList();
        }
        return null;
    }
}
