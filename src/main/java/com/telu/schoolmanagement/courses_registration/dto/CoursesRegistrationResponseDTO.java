package com.telu.schoolmanagement.courses_registration.dto;

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
public class CoursesRegistrationResponseDTO implements Serializable {
    private Long id;
    //TODO: Change to StudentResponseDTO
    private Long studentId;
    //TODO: Change to CourseAssignmentResponseDTO
    private Long courseAssignmentId;
    private boolean isApproved;
    private String academicYear;
    private GeneralCreatedUpdatedBy createdBy;
    private GeneralCreatedUpdatedBy updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

