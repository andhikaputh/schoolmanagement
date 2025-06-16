package com.telu.schoolmanagement.course_registrations.mapper;

import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
import com.telu.schoolmanagement.course_registrations.dto.CourseRegistrationsResponseDTO;
import com.telu.schoolmanagement.course_registrations.model.CourseRegistrations;
import com.telu.schoolmanagement.students.mapper.StudentMapper;

public class CourseRegistrationsMapper {

    public static CourseRegistrationsResponseDTO toDTO(CourseRegistrations courseRegistrations) {
        return CourseRegistrationsResponseDTO.builder()
                .id(courseRegistrations.getId())

                .student(
                        StudentMapper.toDto(courseRegistrations.getStudent())
                )

                .courseAssignmentId(courseRegistrations.getCourseAssignmentId())
                .isApproved(courseRegistrations.isApproved())
                .academicYear(courseRegistrations.getAcademicYear())

                .createdBy(courseRegistrations.getCreatedBy() != null ? GeneralCreatedUpdatedBy.builder()
                                .id(courseRegistrations.getCreatedBy().getId())
                                .name(courseRegistrations.getCreatedBy().getName())
                                .build() : null)
                .updatedBy(courseRegistrations.getUpdatedBy() != null ? GeneralCreatedUpdatedBy.builder()
                                .id(courseRegistrations.getUpdatedBy().getId())
                                .name(courseRegistrations.getUpdatedBy().getName())
                                .build() : null)

                .createdAt(courseRegistrations.getCreatedAt())
                .updatedAt(courseRegistrations.getUpdatedAt())
                .build();
    }
}
