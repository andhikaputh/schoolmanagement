package com.telu.schoolmanagement.roles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesResponseDTO implements Serializable {
    Long id;
    String name;
    String description;
    Long createdBy;
    Long updateBy;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
