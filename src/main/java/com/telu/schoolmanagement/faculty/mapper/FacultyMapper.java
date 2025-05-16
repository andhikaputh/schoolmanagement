package com.telu.schoolmanagement.faculty.mapper;

import com.telu.schoolmanagement.faculty.dto.FacultyResponseDTO;
import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
import com.telu.schoolmanagement.faculty.model.Faculties;

public class FacultyMapper {
    public static FacultyResponseDTO toDTO(Faculties faculty){
        return FacultyResponseDTO.builder()
                .id(faculty.getId())
                .facultyName(faculty.getName())
                .createdBy(new GeneralCreatedUpdatedBy(
                        faculty.getCreatedBy(),
                        "nunggu om"
                ))
                .updatedBy(new GeneralCreatedUpdatedBy(
                        faculty.getUpdatedBy(),
                        "nunggu roles"
                ))
                .createdAt(faculty.getCreatedAt())
                .updatedAt(faculty.getUpdatedAt())
                .build();
    }
}
