package com.telu.schoolmanagement.auth.service;

import com.telu.schoolmanagement.auth.dto.AuthResponse;
import com.telu.schoolmanagement.auth.dto.LoginRequest;
import com.telu.schoolmanagement.auth.dto.RegisterRequest;
import com.telu.schoolmanagement.common.security.jwt.JWTConfig;
import com.telu.schoolmanagement.roles.model.Roles;
import com.telu.schoolmanagement.users.mapper.UsersMapper;
import com.telu.schoolmanagement.users.model.Users;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import com.telu.schoolmanagement.users.util.UsersUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JWTConfig jwtConfig;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsersUtil usersUtil;

    @Transactional
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getNip(),
                        request.getPassword()
                )
        );

        var user = usersRepository.getUsersByNip(request.getNip())
                .orElseThrow(() -> new RuntimeException("User with NIP " + request.getNip() + " not found"));
        var jwt = jwtConfig.generateToken(user);

        return AuthResponse.builder()
                .token(jwt)
                .user(UsersMapper.toDTO(user))
                .build();
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {

        Roles roles = usersUtil.findRoleById(request.getRoleId());

        var user = Users.builder()
                .nip(request.getNip())
                .name(request.getName())
                .isActive(request.getIsActive())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .role(roles)
                .isActive(true)
                .updatedAt(LocalDateTime.now())
                .build();

        user.setCreatedBy(user);
        user.setUpdatedBy(user);
        usersRepository.save(user);

        var jwt = jwtConfig.generateToken(user);
        return AuthResponse.builder()
                .token(jwt)
                .user(UsersMapper.toDTO(user))
                .build();
    }

}
