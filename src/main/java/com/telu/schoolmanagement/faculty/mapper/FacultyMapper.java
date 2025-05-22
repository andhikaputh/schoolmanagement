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
                        faculty.getCreatedBy().getId(),
                        faculty.getCreatedBy().getName()
                ))
                .updatedBy(new GeneralCreatedUpdatedBy(
                        faculty.getUpdatedBy().getId(),
                        faculty.getCreatedBy().getName()
                ))
                .createdAt(faculty.getCreatedAt())
                .updatedAt(faculty.getUpdatedAt())
                .build();
    }
}
