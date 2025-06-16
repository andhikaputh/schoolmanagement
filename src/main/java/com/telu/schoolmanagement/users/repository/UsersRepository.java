package com.telu.schoolmanagement.users.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telu.schoolmanagement.users.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> getUsersByNip(String nip);
    List<Users> findByNameIgnoreCaseContaining(String name);
    List<Users> getUsersByIsActive(Boolean active);
    Boolean existsByNip(String nip);
}
