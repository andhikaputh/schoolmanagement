package com.telu.schoolmanagement.grades.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GradeRequestDTO {
    @NotNull(message = "courseRegistrationId cannot be null")
    private long courseRegistrationId;
    private double assignmentScore;
    private double midtermScore;
    private double finalScore;
    private String finalGrade;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
