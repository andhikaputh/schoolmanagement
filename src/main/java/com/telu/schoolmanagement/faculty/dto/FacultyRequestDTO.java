package com.telu.schoolmanagement.faculty.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FacultyRequestDTO {
    @NotBlank(message = "Faculty cannot be blank")
    String facultyName;
}
