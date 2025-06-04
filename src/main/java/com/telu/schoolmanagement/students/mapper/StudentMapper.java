package com.telu.schoolmanagement.students.mapper;

import com.telu.schoolmanagement.lecturers.dto.UserMinimalResponseDTO;
import com.telu.schoolmanagement.students.dto.ProgramMinimalResponseDTO;
import com.telu.schoolmanagement.students.dto.StudentResponseDTO;
import com.telu.schoolmanagement.students.model.Students;

public class StudentMapper {
    public static StudentResponseDTO toDto(Students students){
        return StudentResponseDTO.builder()
                .id(students.getId())
                .nim(students.getNim())
                .semester(students.getSemester())
                .graduateAt(students.getGraduateAt())
                .userId(students.getUsers().getId())
                .user(UserMinimalResponseDTO.builder()
                        .name(students.getUsers().getName())
                        .nip(students.getUsers().getNip())
                        .build())
                .program(ProgramMinimalResponseDTO.builder()
                        .id(students.getPrograms().getId())
                        .name(students.getPrograms().getName())
                        .build())
                .build();
    }
}
