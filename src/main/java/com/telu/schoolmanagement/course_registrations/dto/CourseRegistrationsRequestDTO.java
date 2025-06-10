package com.telu.schoolmanagement.course_registrations.dto;

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
public class CourseRegistrationsRequestDTO {
    @NotNull(message = "Student ID cannot be null")
    @Min(value = 1, message = "Student ID must be greater than 0")
    private Long studentId;

    @NotNull(message = "Course Assignment ID cannot be null")
    @Min(value = 1, message = "Course Assignment ID must be greater than 0")
    private Long courseAssignmentId;

    private boolean isApproved = false;

    @NotBlank(message = "Academic Year cannot be blank")
    private String academicYear;

    private Long createdBy;

    private Long updatedBy;
}
