package com.telu.schoolmanagement.users.mapper;

import com.telu.schoolmanagement.users.dto.UsersResponseDTO;
import com.telu.schoolmanagement.users.model.Users;

public class UsersMapper {
    public static UsersResponseDTO toDTO(Users users) {
        return UsersResponseDTO.builder()
                .id(users.getId())
                .nip(users.getNip())
                .name(users.getName())
                .password(users.getPassword())
                .isActive(users.getIsActive())
                .graduateAt(users.getGraduateAt())
                .createdBy(users.getCreatedBy())
                .updatedBy(users.getUpdatedBy())
                .createdAt(users.getCreatedAt())
                .updatedAt(users.getUpdatedAt())
                .build();
    }
}
