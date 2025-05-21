package com.telu.schoolmanagement.users.dto;

import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
import com.telu.schoolmanagement.courses.model.Courses;
import com.telu.schoolmanagement.faculty.model.Faculties;
import com.telu.schoolmanagement.roles.model.Roles;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UsersRequestDTO {

    @NotBlank(message = "Nip Users cannot be blank")
    @Size(max = 20,  message = "Max Nip is 20 characters")
    private String nip;

    @NotBlank(message = "Password Users cannot be blank")
    private String password;

    @NotBlank(message = "Name Users cannot be blank")
    private String name;

    private Long roles;
    private Long program;
    private Long faculties;
    private Long courses;

    private Boolean isActive;
    private LocalDate graduateAt;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
