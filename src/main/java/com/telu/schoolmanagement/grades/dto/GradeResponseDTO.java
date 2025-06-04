package com.telu.schoolmanagement.grades.dto;

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
public class GradeResponseDTO implements Serializable {
    private long id;
    private long krsId;
    private double assignmentScore;
    private double midtermScore;
    private double finalScore;
    private String finalGrade;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
