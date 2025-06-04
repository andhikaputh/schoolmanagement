package com.telu.schoolmanagement.users.util;

import com.telu.schoolmanagement.program.model.Programs;
import com.telu.schoolmanagement.program.repository.ProgramRepository;
import com.telu.schoolmanagement.roles.model.Roles;
import com.telu.schoolmanagement.roles.repository.RolesRepository;
import com.telu.schoolmanagement.users.model.Users;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsersUtil {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    ProgramRepository programRepository;

    @Transactional
    public Users findUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User ID " + id + " doesn't exist"));
    }

    @Transactional
    public Roles findRoleById(Long id) {
        return rolesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role ID " + id + " doesn't exist"));
    }
}
