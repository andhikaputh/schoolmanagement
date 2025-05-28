package com.telu.schoolmanagement.students.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramMinimalResponseDTO {
    private Long id;
    private String name;
}
