package com.telu.schoolmanagement.grades.repository;

import com.telu.schoolmanagement.grades.model.Grades;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grades,Long> {
}
