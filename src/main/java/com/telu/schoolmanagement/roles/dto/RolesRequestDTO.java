package com.telu.schoolmanagement.roles.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RolesRequestDTO {
    @NotBlank(message = "Roles cannot blank")
    String name;
    String description;
    Long createdBy;
    Long updateBy;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
