package com.telu.schoolmanagement.course_registrations.repository;

import com.telu.schoolmanagement.course_registrations.model.CourseRegistrations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRegistrationsRepository extends JpaRepository<CourseRegistrations, Long> {}
