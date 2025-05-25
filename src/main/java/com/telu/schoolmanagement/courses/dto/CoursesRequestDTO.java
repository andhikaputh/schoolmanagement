package com.telu.schoolmanagement.courses.dto;

import com.telu.schoolmanagement.common.request.CreateGroup;
import com.telu.schoolmanagement.common.request.UpdateGroup;
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

    @Min(value= 1, message = "Minimum SKS is 1")
    private int sks;

    @NotNull(message = "Program ID cannot be null")
    private Long programId;

    @NotNull(message = "Created By cannot be null", groups = CreateGroup.class)
    private Long createdBy;

    @NotNull(message = "Updated By cannot be null", groups = UpdateGroup.class)
    private Long updatedBy;
}
