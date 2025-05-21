package com.telu.schoolmanagement.users.dto;

import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDTO {
    private Long id;
    private String nip;
    private String name;

    private RoleSummary roles;
    private ProgramSummary program;
    private FacultySummary faculties;

    private Boolean isActive;
    private LocalDate graduateAt;
    private GeneralCreatedUpdatedBy createdBy;
    private GeneralCreatedUpdatedBy updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //    Getter for role id and name only
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoleSummary {
        private Long id;
        private String name;
    }

    //    Getter for faculty id and name only
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FacultySummary {
        private Long id;
        private String name;
    }

    //    Getter for program id and name only
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProgramSummary {
        private Long id;
        private String name;
    }
}
