package com.telu.schoolmanagement.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDTO {
    private Long id;
    private String nip;
    private String password;
    private String name;

    // Role, Program

    private Boolean isActive;
    private LocalDate graduateAt;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
