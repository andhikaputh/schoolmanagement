package com.telu.schoolmanagement.program.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProgramRequestDTO {

    @NotBlank(message = "Progarm cannot be null")
    private String name;

    @NotBlank(message = "Faculty Id cannot be null")
    private Long facultyId;


    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
