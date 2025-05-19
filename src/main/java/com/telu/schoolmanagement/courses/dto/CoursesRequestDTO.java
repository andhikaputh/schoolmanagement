package com.telu.schoolmanagement.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursesRequestDTO {
    private String name;
    private int sks;
    private int programId;
    private Long createdBy;
    private Long updatedBy;
}
