package com.telu.schoolmanagement.auth.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Nip Users cannot be blank")
    @Size(max = 20,  message = "Max Nip is 20 characters")
    private String nip;

    @NotBlank(message = "Password Users cannot be blank")
    private String password;

    @NotBlank(message = "Name Users cannot be blank")
    private String name;

    // Role, Program
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    private Boolean isActive;
    private LocalDate graduateAt;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}