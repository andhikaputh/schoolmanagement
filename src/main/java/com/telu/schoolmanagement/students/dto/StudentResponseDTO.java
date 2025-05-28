package com.telu.schoolmanagement.students.dto;

import com.telu.schoolmanagement.lecturers.dto.UserMinimalResponseDTO;
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
public class StudentResponseDTO  implements Serializable {
    private Long id;
    private String nim;
    private Integer semester;
    private LocalDateTime graduateAt;
    private Long userId;
    private UserMinimalResponseDTO user;
    private ProgramMinimalResponseDTO program;
}
