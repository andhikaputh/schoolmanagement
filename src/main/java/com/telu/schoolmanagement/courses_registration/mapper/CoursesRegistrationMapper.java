package com.telu.schoolmanagement.courses_registration.mapper;

import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
import com.telu.schoolmanagement.courses.dto.CoursesResponseDTO;
import com.telu.schoolmanagement.courses_registration.dto.CoursesRegistrationResponseDTO;
import com.telu.schoolmanagement.courses_registration.model.CoursesRegistration;

public class CoursesRegistrationMapper {

    public static CoursesRegistrationResponseDTO toDTO(CoursesRegistration coursesRegistration) {
        return CoursesRegistrationResponseDTO.builder()
                .id(coursesRegistration.getId())
                .studentId(coursesRegistration.getStudentId())
                .courseAssignmentId(coursesRegistration.getCourseAssignmentId())
                .isApproved(coursesRegistration.isApproved())
                .academicYear(coursesRegistration.getAcademicYear())

                .createdBy(coursesRegistration.getCreatedBy() != null ? GeneralCreatedUpdatedBy.builder()
                                .id(coursesRegistration.getCreatedBy().getId())
                                .name(coursesRegistration.getCreatedBy().getName())
                                .build() : null)
                .updatedBy(coursesRegistration.getUpdatedBy() != null ? GeneralCreatedUpdatedBy.builder()
                                .id(coursesRegistration.getUpdatedBy().getId())
                                .name(coursesRegistration.getUpdatedBy().getName())
                                .build() : null)
                .createdAt(coursesRegistration.getCreatedAt())
                .updatedAt(coursesRegistration.getUpdatedAt())
                .build();
    }
}
