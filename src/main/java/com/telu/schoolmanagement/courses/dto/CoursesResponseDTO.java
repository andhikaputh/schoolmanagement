package com.telu.schoolmanagement.courses.dto;

import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
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

    //TODO change to ProgramResponseDTO after program merged to master
    private ProgramSummary program;
    private GeneralCreatedUpdatedBy createdBy;
    private GeneralCreatedUpdatedBy updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProgramSummary {
        private Long id;
        private String name;
    }
}

