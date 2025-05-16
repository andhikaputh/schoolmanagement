package com.telu.schoolmanagement.program.service;

import com.telu.schoolmanagement.program.dto.ProgramRequestDTO;
import com.telu.schoolmanagement.program.dto.ProgramResponseDTO;
import com.telu.schoolmanagement.program.mapper.ProgramMapper;
import com.telu.schoolmanagement.program.model.Program;
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

        Program program = Program.builder()
                .name(request.getName())
                .facultyId(request.getFacultyId())
                .createdAt(request.getCreatedAt())
                .createdBy(request.getCreatedBy())
                .build();
    }

    public void deleteProgram(Long id) {
        if (!programRepository.existsById(id)) {
            throw new EntityNotFoundException("Program dengan id " + id + " tidak ada");
        }

        programRepository.deleteById(id);
    }

    public void updateProgram(Long id, ProgramRequestDTO request) {

        Program newProgram = programRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Program dengan Id " + id + " tidak tersedia"));

        newProgram.setName(request.getName());
        newProgram.setFacultyId(request.getFacultyId());
        newProgram.setUpdatedBy(request.getUpdatedBy());
        newProgram.setUpdatedAt(request.getUpdatedAt());
    }
}
