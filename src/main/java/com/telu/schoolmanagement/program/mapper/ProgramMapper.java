package com.telu.schoolmanagement.program.mapper;

import com.telu.schoolmanagement.faculty.mapper.FacultyMapper;
import com.telu.schoolmanagement.faculty.model.Faculties;
import com.telu.schoolmanagement.program.dto.ProgramResponseDTO;
import com.telu.schoolmanagement.program.model.Programs;

public class ProgramMapper {
    public static ProgramResponseDTO toDTO(Programs programs) {
        return ProgramResponseDTO.builder()
                .id(programs.getId())
                .name(programs.getName())
                .faculties(
                        FacultyMapper.toDTO(programs.getFaculty())
                )
                .createdBy(programs.getCreatedBy())
                .updatedBy(programs.getUpdatedBy())
                .createdAt(programs.getCreatedAt())
                .updatedAt(programs.getUpdatedAt())
                .build();

    }
}
