package com.telu.schoolmanagement.course_registrations.dto;

import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
import com.telu.schoolmanagement.students.dto.StudentResponseDTO;
import com.telu.schoolmanagement.students.model.Students;
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
public class CourseRegistrationsResponseDTO implements Serializable {
    private Long id;
    private StudentResponseDTO student;
    //TODO: Change to CourseAssignmentResponseDTO
    private Long courseAssignmentId;
    private boolean isApproved;
    private String academicYear;
    private GeneralCreatedUpdatedBy createdBy;
    private GeneralCreatedUpdatedBy updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

