package com.telu.schoolmanagement.program.dto;

import com.telu.schoolmanagement.faculty.model.Faculties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramResponseDTO implements Serializable {
    private Long id;
    private String name;
    private Faculties faculties;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
