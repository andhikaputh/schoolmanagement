package com.telu.schoolmanagement.users.service;

import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.common.response.PaginationResponse;
import com.telu.schoolmanagement.faculty.repository.FacultyRepository;
import com.telu.schoolmanagement.program.repository.ProgramRepository;
import com.telu.schoolmanagement.roles.model.Roles;
import com.telu.schoolmanagement.roles.repository.RolesRepository;
import com.telu.schoolmanagement.users.dto.UsersRequestDTO;
import com.telu.schoolmanagement.users.dto.UsersResponseDTO;
import com.telu.schoolmanagement.users.mapper.UsersMapper;
import com.telu.schoolmanagement.users.model.Users;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import com.telu.schoolmanagement.users.util.UsersUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersUtil usersUtil;

    @Transactional
    public List<UsersResponseDTO> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(UsersMapper::toDTO)
                .toList();
    }

    @Transactional
    public ApiResponse<List<UsersResponseDTO>> getAllUsersWithPagination(int page, int size) {

        int adjustedPage = (page > 0) ? page - 1 : 0;

        Pageable pageable = PageRequest.of(adjustedPage, size, Sort.by("createdAt").descending());
        Page<Users> userPage = usersRepository.findAll(pageable);

        List<UsersResponseDTO> userDTOs = userPage.getContent().stream()
                .map(UsersMapper::toDTO)
                .toList();

        PaginationResponse pagination = new PaginationResponse(
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.hasNext(),
                userPage.hasPrevious()
        );

        return new ApiResponse<>(
                true,
                "success",
                userDTOs,
                pagination
        );
    }

    @Transactional
    public UsersResponseDTO getUsersById(Long id) {
        return UsersMapper.toDTO(usersRepository.findById(id).orElseThrow());
    }

    @Transactional
    public UsersResponseDTO getUsersByNip(String nip) {
        return UsersMapper.toDTO(usersRepository.getUsersByNip(nip).orElseThrow());
    }

    @Transactional
    public List<UsersResponseDTO> getUserByName(String name) {
        return usersRepository.findByNameIgnoreCaseContaining(name).stream()
                .map(UsersMapper::toDTO)
                .toList();
    }

    @Transactional
    public List<UsersResponseDTO> getUserByIsAct(Boolean active) {
        return usersRepository.getUsersByIsActive(active).stream()
                .map(UsersMapper::toDTO)
                .toList();
    }

    @Transactional
    public void updateUsers(Long id, UsersRequestDTO req) {
        Users newUser = usersUtil.findUserById(id);
        Users updatedBy = usersUtil.findUserById(req.getUpdatedBy());
        Roles roles = usersUtil.findRoleById(req.getRoles());

        newUser.setNip(req.getNip());
        newUser.setPassword(passwordEncoder.encode(req.getPassword()));
        newUser.setName(req.getName());
        newUser.setRole(roles);

        newUser.setIsActive(req.getIsActive());
        newUser.setUpdatedBy(updatedBy);

        usersRepository.save(newUser);

    }

    @Transactional
    public void deleteUsers(Long id) {
        usersUtil.findUserById(id);
        usersRepository.deleteById(id);
    }
}
