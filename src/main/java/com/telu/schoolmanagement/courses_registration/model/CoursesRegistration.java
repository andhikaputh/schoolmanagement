package com.telu.schoolmanagement.courses_registration.model;

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
@AllArgsConstructor
@NoArgsConstructor
public class CoursesRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


//    @ManyToOne
//    @JoinColumn(name = "student_id", referencedColumnName = "id")
//    private Student student;
    private Long studentId;

//    @ManyToOne
//    @JoinColumn(name = "course_assignment_id", referencedColumnName = "id")
//    private CourseAssignment courseAssignment;
    private Long courseAssignmentId;

    @Column(name = "is_approved", defaultValue = "false")
    private boolean isApproved;

    @Column(name = "academic_year")
    private String academicYear;


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
