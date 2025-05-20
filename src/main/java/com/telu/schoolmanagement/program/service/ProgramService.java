package com.telu.schoolmanagement.program.service;

import com.telu.schoolmanagement.faculty.model.Faculties;
import com.telu.schoolmanagement.faculty.repository.FacultyRepository;
import com.telu.schoolmanagement.program.dto.ProgramRequestDTO;
import com.telu.schoolmanagement.program.dto.ProgramResponseDTO;
import com.telu.schoolmanagement.program.mapper.ProgramMapper;
import com.telu.schoolmanagement.program.model.Programs;
import com.telu.schoolmanagement.program.repository.ProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public List<ProgramResponseDTO> getAllProgram() {
        return programRepository.findAll().stream()
                .map(ProgramMapper::toDTO)
                .toList();
    }

    public ProgramResponseDTO getProgramById(Long id) {
        return ProgramMapper.toDTO(programRepository.findById(id).orElseThrow());
    }

    public List<ProgramResponseDTO> getProgramByName(String name) {
        return programRepository.findByNameIgnoreCaseContaining(name).stream()
                .map(ProgramMapper::toDTO)
                .toList();
    }

    public void createProgram(ProgramRequestDTO request) {

        Faculties faculties = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new RuntimeException("faculty_id not found"));

        Programs programs = Programs.builder()
                .name(request.getName())
                .faculty(faculties)
                .createdAt(request.getCreatedAt())
                .createdBy(request.getCreatedBy())
                .updatedBy(request.getUpdatedBy())
                .updatedAt(request.getUpdatedAt())
                .build();

        programRepository.save(programs);
    }

    public void deleteProgram(Long id) {
        if (!programRepository.existsById(id)) {
            throw new EntityNotFoundException("Program with id " + id + " doesn't exist");
        }

        programRepository.deleteById(id);
    }

    public void updateProgram(Long id, ProgramRequestDTO request) {

        Programs newPrograms = programRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Program with Id " + id + " doesn't exist"));

        Faculties faculties = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new RuntimeException("faculty_id not found"));

        newPrograms.setName(request.getName());
        newPrograms.setFaculty(faculties);
        newPrograms.setUpdatedBy(request.getUpdatedBy());
        newPrograms.setUpdatedAt(request.getUpdatedAt());
        newPrograms.setCreatedAt(request.getCreatedAt());
        newPrograms.setUpdatedBy(request.getUpdatedBy());

        programRepository.save(newPrograms);
    }
}
