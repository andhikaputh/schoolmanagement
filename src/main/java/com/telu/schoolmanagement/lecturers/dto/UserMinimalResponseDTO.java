package com.telu.schoolmanagement.lecturers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMinimalResponseDTO {
    private Long id;
    private String nip;
    private String name;
    private String slug;
}
