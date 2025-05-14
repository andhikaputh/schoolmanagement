package com.telu.schoolmanagement.users.repository;

import com.telu.schoolmanagement.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> getUsersByNip(String nip);
    List<Users> getByNameContaining(String name);

    // Role, Program

    List<Users> getUsersByIsActive(Boolean active);
    List<Users> getUsersByGraduateAt(LocalDate date);
}
