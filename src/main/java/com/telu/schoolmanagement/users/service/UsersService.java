package com.telu.schoolmanagement.users.service;

import com.telu.schoolmanagement.faculty.model.Faculties;
import com.telu.schoolmanagement.faculty.repository.FacultyRepository;
import com.telu.schoolmanagement.program.model.Programs;
import com.telu.schoolmanagement.program.repository.ProgramRepository;
import com.telu.schoolmanagement.roles.model.Roles;
import com.telu.schoolmanagement.roles.repository.RolesRepository;
import com.telu.schoolmanagement.users.dto.UsersRequestDTO;
import com.telu.schoolmanagement.users.dto.UsersResponseDTO;
import com.telu.schoolmanagement.users.mapper.UsersMapper;
import com.telu.schoolmanagement.users.model.Users;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<UsersResponseDTO> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(UsersMapper::toDTO)
                .toList();
    }

    public UsersResponseDTO getUsersById(Long id) {
        return UsersMapper.toDTO(usersRepository.findById(id).orElseThrow());
    }

    public UsersResponseDTO getUsersByNip(String nip) {
        return UsersMapper.toDTO(usersRepository.getUsersByNip(nip).orElseThrow());
    }

    public List<UsersResponseDTO> getUserByName(String name) {
        return usersRepository.getByNameContaining(name).stream()
                .map(UsersMapper::toDTO)
                .toList();
    }

    public List<UsersResponseDTO> getUserByIsAct(Boolean active) {
        return usersRepository.getUsersByIsActive(active).stream()
                .map(UsersMapper::toDTO)
                .toList();
    }

    public List<UsersResponseDTO> getUsersByGrad(LocalDate date) {
        return usersRepository.getUsersByGraduateAt(date).stream()
                .map(UsersMapper::toDTO)
                .toList();
    }

    public void updateUsers(Long id, UsersRequestDTO req) {
        Users newUser = findUserById(id);
        Users updatedBy = findUserById(req.getUpdatedBy());
        Roles roles = findRoleById(req.getRoles());
        Programs programs = findProgramById(req.getProgram());
        Faculties faculties = findFacultyById(req.getFaculties());

        newUser.setNip(req.getNip());
        newUser.setPassword(passwordEncoder.encode(req.getPassword()));
        newUser.setName(req.getName());
        newUser.setRole(roles);
        newUser.setProgram(programs);

        newUser.setFaculty(faculties);
        newUser.setIsActive(req.getIsActive());
        newUser.setGraduateAt(req.getGraduateAt());
        newUser.setUpdatedBy(updatedBy);

        usersRepository.save(newUser);

    }
    public void deleteUsers(Long id) {
        Users newUser = findUserById(id);

        usersRepository.deleteById(id);
    }

//    Service Util
    private Users findUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User ID " + id + " doesn't exist"));
    }

    private Roles findRoleById(Long id) {
        return rolesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role ID " + id + " doesn't exist"));
    }

    private Programs findProgramById(Long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program ID " + id + " doesn't exist"));
    }

    private Faculties findFacultyById(Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Faculty ID " + id + " doesn't exist"));
    }

}
