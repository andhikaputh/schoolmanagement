package com.telu.schoolmanagement.faculty.service;

import com.telu.schoolmanagement.faculty.dto.FacultyRequestDTO;
import com.telu.schoolmanagement.faculty.dto.FacultyResponseDTO;
import com.telu.schoolmanagement.faculty.mapper.FacultyMapper;
import com.telu.schoolmanagement.faculty.model.Faculties;
import com.telu.schoolmanagement.faculty.repository.FacultyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public List<FacultyResponseDTO> getAllFaculty(){
        return facultyRepository.findAll().stream()
                .map(FacultyMapper::toDTO)
                .toList();
    }

    public FacultyResponseDTO getFacultyById(Long id){
       return FacultyMapper.toDTO(facultyRepository.findById(id).orElseThrow());
    }

    public List<FacultyResponseDTO> getFacultyByName(String query){
        return facultyRepository.getByNameContaining(query).stream()
                .map(FacultyMapper::toDTO)
                .toList();
    }

    public void createFaculty(FacultyRequestDTO requestDTO) {

        Faculties faculty = Faculties.builder()
                .name(requestDTO.getFacultyName())
                .build();

        facultyRepository.save(faculty);
    }

    public void deleteFacultyById(Long id) {
        if (!facultyRepository.existsById(id)) {
            throw new EntityNotFoundException("Faculty dengan ID " + id + " tidak ditemukan.");
        }
        facultyRepository.deleteById(id);
    }

    public void updateFaculty(Long id, FacultyRequestDTO requestDTO) {
        Faculties newFaculty = facultyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Faculty dengan ID " + id + " tidak ditemukan."));

        newFaculty.setName(requestDTO.getFacultyName());
        facultyRepository.save(newFaculty);
    }

}
