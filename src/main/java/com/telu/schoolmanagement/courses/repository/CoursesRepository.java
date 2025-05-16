package com.telu.schoolmanagement.courses.repository;

import com.telu.schoolmanagement.courses.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Long> {}
