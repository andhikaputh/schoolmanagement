package com.telu.schoolmanagement.roles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesResponseDTO {
    Long id;
    String name;
    String description;
    Long createdBy;
    Long updateBy;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
