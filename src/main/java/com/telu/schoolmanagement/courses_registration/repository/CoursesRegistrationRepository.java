package com.telu.schoolmanagement.courses_registration.repository;

import com.telu.schoolmanagement.courses.model.Courses;
import com.telu.schoolmanagement.courses_registration.model.CoursesRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRegistrationRepository extends JpaRepository<Courses, Long> {}
