package com.telu.schoolmanagement.grades.mapper;

import com.telu.schoolmanagement.grades.dto.GradeResponseDTO;
import com.telu.schoolmanagement.grades.model.Grades;

public class GradeMapper {
    public static GradeResponseDTO toDTO(Grades grades){
        return GradeResponseDTO.builder()
                .id(grades.getId())
                .krsId(grades.getKrsId())
                .finalGrade(grades.getFinalGrade())
                .assignmentScore(grades.getAssignmentScore())
                .midtermScore(grades.getMidtermScore())
                .finalScore(grades.getFinalScore())
                .updatedBy(grades.getUpdatedBy())
                .createdBy(grades.getCreatedBy())
                .updatedAt(grades.getUpdatedAt())
                .createdAt(grades.getCreatedAt())
                .build();
    }
}
