package com.telu.schoolmanagement.users.service;

import com.telu.schoolmanagement.users.dto.UsersResponseDTO;
import com.telu.schoolmanagement.users.mapper.UsersMapper;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

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

//    public void createUsers(UsersRequestDTO req) {}
//    public void updateUsers(Long id, UsersRequestDTO req) {}
//    public void deleteUsers(Long id) {}
}
