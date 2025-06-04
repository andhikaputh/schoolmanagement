package com.telu.schoolmanagement.lecturers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.telu.schoolmanagement.faculty.model.Faculties;
import com.telu.schoolmanagement.users.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecturers {
    @Id
    @Column(name = "nidn", nullable = false, unique = true, length = 20)
    private String nidn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculties faculty;




}
