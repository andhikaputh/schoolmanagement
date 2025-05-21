package com.telu.schoolmanagement.faculty.dto;

import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
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
public class FacultyResponseDTO implements Serializable {
    private Long id;
    private String facultyName;
    private GeneralCreatedUpdatedBy createdBy;
    private GeneralCreatedUpdatedBy updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
