package com.telu.schoolmanagement.courses.dto;

import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
import com.telu.schoolmanagement.program.dto.ProgramResponseDTO;
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
public class CoursesResponseDTO implements Serializable {
    private Long id;
    private String name;
    private String slug;
    private int sks;
    private ProgramResponseDTO program;
    private GeneralCreatedUpdatedBy createdBy;
    private GeneralCreatedUpdatedBy updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

