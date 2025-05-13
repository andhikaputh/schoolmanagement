package com.telu.schoolmanagement.courses.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int sks;

    //    refer to programs table
    private int program_id;

    //    refer to users table
    private int created_by;
    private int updated_by;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
