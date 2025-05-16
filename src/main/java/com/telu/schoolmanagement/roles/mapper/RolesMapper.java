package com.telu.schoolmanagement.roles.mapper;

import com.telu.schoolmanagement.roles.dto.RolesResponseDTO;
import com.telu.schoolmanagement.roles.model.Roles;

public class RolesMapper {

    public static RolesResponseDTO toDTO(Roles roles){
        return RolesResponseDTO.builder()
                .id(roles.getId())
                .name(roles.getName())
                .description(roles.getDescription())
                .createdBy(roles.getCreatedBy())
                .updateBy(roles.getUpdatedBy())
                .createdAt(roles.getCreatedAt())
                .updatedAt(roles.getUpdatedAt())
                .build();
    }
}
