package com.telu.schoolmanagement.students.repository;

import com.telu.schoolmanagement.students.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository  extends JpaRepository<Students,Long> {
    Optional<Students> findByNim(String nim);
    boolean existsByNim(String nim);
    List<Students> findByNimIgnoreCaseContaining(String keyword);
}
