package com.telu.schoolmanagement.courses_registration.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursesRegistrationRequestDTO {
    @NotNull(message = "Student ID cannot be null")
    @Min(value = 1, message = "Student ID must be greater than 0")
    private Long studentId;

    @NotNull(message = "Course Assignment ID cannot be null")
    @Min(value = 1, message = "Course Assignment ID must be greater than 0")
    private Long courseAssignmentId;

    //Question: Should this be included in the request? or should it be set by DB table default?
    //private boolean isApproved;

    @NotBlank(message = "Academic Year cannot be blank")
    private String academicYear;

    @NotBlank(message = "Created By cannot be blank")
    private String createdBy;

    @NotBlank(message = "Updated By cannot be blank")
    private String updatedBy;
}
