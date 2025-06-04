package com.telu.schoolmanagement.courses.mapper;

import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
import com.telu.schoolmanagement.courses.dto.CoursesResponseDTO;
import com.telu.schoolmanagement.courses.model.Courses;
import com.telu.schoolmanagement.program.mapper.ProgramMapper;

public class CoursesMapper {

    public static CoursesResponseDTO toDTO(Courses courses) {
        return CoursesResponseDTO.builder()
                .id(courses.getId())
                .name(courses.getName())
                .code(courses.getCode())
                .semester(courses.getSemester())
                .credit(courses.getCredit())
                .program(
                        ProgramMapper.toDTO(courses.getPrograms())
                )
                .createdBy(courses.getCreatedBy() != null ? GeneralCreatedUpdatedBy.builder()
                                .id(courses.getCreatedBy().getId())
                                .name(courses.getCreatedBy().getName())
                                .build() : null)
                .updatedBy(courses.getUpdatedBy() != null ? GeneralCreatedUpdatedBy.builder()
                                .id(courses.getUpdatedBy().getId())
                                .name(courses.getUpdatedBy().getName())
                                .build() : null)
                .createdAt(courses.getCreatedAt())
                .updatedAt(courses.getUpdatedAt())
                .build();
    }
}
