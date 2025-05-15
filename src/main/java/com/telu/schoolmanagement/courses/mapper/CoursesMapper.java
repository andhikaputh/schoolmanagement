package com.telu.schoolmanagement.courses.mapper;

import com.telu.schoolmanagement.courses.dto.CoursesResponseDTO;
import com.telu.schoolmanagement.courses.model.Courses;

public class CoursesMapper {

    public static CoursesResponseDTO toDTO(Courses courses) {
        return CoursesResponseDTO.builder()
                .id(courses.getId())
                .name(courses.getName())
                .sks(courses.getSks())
                .programId(courses.getProgramId())
                .createdBy(courses.getCreatedBy())
                .updatedBy(courses.getUpdatedBy())
                .createdAt(courses.getCreatedAt())
                .updatedAt(courses.getUpdatedAt())
                .build();
    }
}
