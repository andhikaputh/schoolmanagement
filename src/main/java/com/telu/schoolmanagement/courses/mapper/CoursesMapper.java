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
                .slug(courses.getSlug())
                .sks(courses.getSks())

                //TODO change to ProgramResponseDTO after program merged to master
                .program(courses.getPrograms() != null ? new CoursesResponseDTO.ProgramSummary(
                        courses.getPrograms().getId(),
                        courses.getPrograms().getName()
                ) : null)

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
