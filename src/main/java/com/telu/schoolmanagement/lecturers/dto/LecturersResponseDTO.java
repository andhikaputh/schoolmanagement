package com.telu.schoolmanagement.lecturers.dto;

import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
import jakarta.validation.constraints.NotBlank;
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
public class LecturersResponseDTO implements Serializable {
    private String nidn;
    private Long user_id;
    private UserMinimalResponseDTO user;
    private FacultyMinimalResponseDTO faculty;
}
