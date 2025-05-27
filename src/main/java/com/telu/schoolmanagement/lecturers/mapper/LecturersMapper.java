package com.telu.schoolmanagement.lecturers.mapper;

import com.telu.schoolmanagement.lecturers.dto.FacultyMinimalResponseDTO;
import com.telu.schoolmanagement.lecturers.dto.LecturersResponseDTO;
import com.telu.schoolmanagement.lecturers.dto.UserMinimalResponseDTO;
import com.telu.schoolmanagement.lecturers.model.Lecturers;

public class LecturersMapper {
    public static LecturersResponseDTO toDTO(Lecturers lecturer) {
        return LecturersResponseDTO.builder()
                .id(lecturer.getId())
                .nidn(lecturer.getNidn())
                .user(UserMinimalResponseDTO.builder()
                        .id(lecturer.getUser().getId())
                        .name(lecturer.getUser().getName())
                        .nip(lecturer.getUser().getNip())
                        .build())
                .faculty(FacultyMinimalResponseDTO.builder()
                        .id(lecturer.getFaculty().getId())
                        .name(lecturer.getFaculty().getName())
                        .slug(lecturer.getFaculty().getSlug())
                        .build())
                .build();
    }
}
