package com.telu.schoolmanagement.courses.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursesRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Code cannot be blank")
    private String code;

    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 8, message = "Semester cannot exceed 8")
    private int semester;

    @Min(value= 1, message = "Minimum credit is 1")
    @Max(value = 4, message = "Maximum credit is 4")
    private int credit;

    @NotNull(message = "Program ID cannot be null")
    private Long programId;

    private Long createdBy;
    private Long updatedBy;
}
