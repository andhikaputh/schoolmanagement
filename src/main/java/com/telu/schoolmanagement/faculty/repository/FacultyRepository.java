package com.telu.schoolmanagement.faculty.repository;

import com.telu.schoolmanagement.faculty.model.Faculties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculties, Long> {
    Faculties getFacultyByName(String name);
    List<Faculties> getByNameContaining(String keyword);
}
