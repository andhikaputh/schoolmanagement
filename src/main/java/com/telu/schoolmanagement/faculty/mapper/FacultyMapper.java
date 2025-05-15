package com.telu.schoolmanagement.faculty.mapper;

import com.telu.schoolmanagement.faculty.dto.FacultyResponseDTO;
import com.telu.schoolmanagement.faculty.model.Faculties;

public class FacultyMapper {
    public static FacultyResponseDTO toDTO(Faculties faculty){
        return FacultyResponseDTO.builder()
                .id(faculty.getId())
                .facultyname(faculty.getName())
                .build();
    }
}
