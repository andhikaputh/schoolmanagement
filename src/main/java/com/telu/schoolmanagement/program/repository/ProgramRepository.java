package com.telu.schoolmanagement.program.repository;

import com.telu.schoolmanagement.program.model.Programs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Programs, Long> {
    List<Programs> findByNameIgnoreCaseContaining(String name);
}
