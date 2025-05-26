package com.telu.schoolmanagement.lecturers.repository;

import com.telu.schoolmanagement.lecturers.model.Lecturers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecturersRepository extends JpaRepository<Lecturers, Long> {
    List<Lecturers> findByNidnIgnoreCaseContaining(String keyword);
}
