package com.telu.schoolmanagement.lecturers.repository;

import com.telu.schoolmanagement.lecturers.model.Lecturers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LecturersRepository extends JpaRepository<Lecturers, Long> {
    Optional<Lecturers> findByNidn(String nidn);
    boolean existsByNidn(String nidn);
    List<Lecturers> findByNidnIgnoreCaseContaining(String keyword);
}
