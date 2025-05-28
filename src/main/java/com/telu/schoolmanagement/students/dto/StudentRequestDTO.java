package com.telu.schoolmanagement.students.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequestDTO {
    @NotBlank(message = "NIM cannot be blank")
    private String nim;

    @NotNull(message = "Semester cannot be null")
    private Integer semester;

    @NotNull(message = "Program ID cannot be null")
    private Long programId;
}
