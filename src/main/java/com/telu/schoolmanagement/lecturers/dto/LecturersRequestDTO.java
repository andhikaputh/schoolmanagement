package com.telu.schoolmanagement.lecturers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LecturersRequestDTO {
    @NotNull(message = "Faculty ID cannot be null")
    private Long facultyId;
}
