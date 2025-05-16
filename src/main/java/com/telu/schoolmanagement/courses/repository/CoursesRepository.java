package com.telu.schoolmanagement.courses.repository;

import com.telu.schoolmanagement.courses.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoursesRepository extends JpaRepository<Courses, Long> {
    List<Courses> findByNameIgnoreCaseContaining(String name);
}
