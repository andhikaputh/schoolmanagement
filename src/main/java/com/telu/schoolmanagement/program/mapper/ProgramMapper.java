package com.telu.schoolmanagement.program.mapper;

import com.telu.schoolmanagement.program.dto.ProgramResponseDTO;
import com.telu.schoolmanagement.program.model.Program;

public class ProgramMapper {
    public static ProgramResponseDTO toDTO(Program program) {
        return ProgramResponseDTO.builder()
                .id(program.getId())
                .name(program.getName())
                .facultyId(program.getFacultyId())
                .createdBy(program.getCreatedBy())
                .updatedBy(program.getUpdatedBy())
                .createdAt(program.getCreatedAt())
                .updatedAt(program.getUpdatedAt())
                .build();

    }
}
