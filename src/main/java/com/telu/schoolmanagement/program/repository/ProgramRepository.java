package com.telu.schoolmanagement.program.repository;

import com.telu.schoolmanagement.program.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByNameIgnoreCaseContaining(String name);
}
