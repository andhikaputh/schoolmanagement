package com.telu.schoolmanagement.roles.repository;

import com.telu.schoolmanagement.roles.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles getRolesByName(String name);

}
