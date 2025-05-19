package com.telu.schoolmanagement.faculty.repository;

import com.telu.schoolmanagement.faculty.model.Faculties;
import com.telu.schoolmanagement.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.telu.schoolmanagement.users.repository.UsersRepository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculties, Long> {
    Faculties getFacultyByName(String name);
    List<Faculties> findByNameIgnoreCaseContaining(String keyword);

}
