package com.telu.schoolmanagement.roles.service;

import com.telu.schoolmanagement.roles.dto.RolesRequestDTO;
import com.telu.schoolmanagement.roles.dto.RolesResponseDTO;
import com.telu.schoolmanagement.roles.mapper.RolesMapper;
import com.telu.schoolmanagement.roles.model.Roles;
import com.telu.schoolmanagement.roles.repository.RolesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    RolesRepository rolesRepository;

    public List<RolesResponseDTO> getAllRoles(){
        return rolesRepository.findAll().stream()
                .map(RolesMapper::toDTO)
                .toList();
    }

    public RolesResponseDTO getRolesById(Long id){
        return RolesMapper.toDTO(rolesRepository.findById(id).orElseThrow());
    }

    public void createRoles(RolesRequestDTO request) {
        Roles roles = Roles.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdBy(request.getCreatedBy())
                .updatedBy(request.getUpdateBy())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .build();

        rolesRepository.save(roles);
    }

    public void deleteRolesById(Long id){
        if (!rolesRepository.existsById(id)){
            throw new EntityNotFoundException(" Roles dengan ID " + id + " tidak ditemukan");
        }
        rolesRepository.deleteById(id);
    }

    public void updateRoles(Long id, RolesRequestDTO requestDTO){
        Roles newRoles = rolesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Roles dengan ID " + id + "tidak ditemukan."));

        newRoles.setName(requestDTO.getName());
        rolesRepository.save(newRoles);
    }
}
