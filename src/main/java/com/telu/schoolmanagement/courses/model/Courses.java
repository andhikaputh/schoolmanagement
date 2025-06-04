package com.telu.schoolmanagement.courses.model;

import com.telu.schoolmanagement.common.util.Util;
import com.telu.schoolmanagement.program.model.Programs;
import com.telu.schoolmanagement.users.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private int semester;
    private int credit;

    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    private Programs programs;

    @ManyToOne
    @JoinColumn(name="created_by", referencedColumnName = "id")
    private Users createdBy;
    @ManyToOne
    @JoinColumn(name="updated_by", referencedColumnName = "id")
    private Users updatedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}