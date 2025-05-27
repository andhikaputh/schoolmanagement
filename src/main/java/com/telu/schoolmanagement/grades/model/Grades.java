package com.telu.schoolmanagement.grades.model;


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
public class Grades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    @JoinColumn(name = "krs_id", referencedColumnName = "id")
    @Column(name = "krs_id")

    private long krsId;
    @Column(name = "assignment_score")
    private double assignmentScore;
    @Column(name = "midterm_score")
    private double midtermScore;
    @Column(name = "final_score")
    private double finalScore;
    @Column(name = "final_grade")
    private String finalGrade;
    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "updated_by")
    private Long updatedBy;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
