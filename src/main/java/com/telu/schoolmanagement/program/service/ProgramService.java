package com.telu.schoolmanagement.program.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.telu.schoolmanagement.common.appconfig.AppConfig;
import com.telu.schoolmanagement.common.redis.RedisCacheUtil;
import com.telu.schoolmanagement.common.constant.AppConstant;
import com.telu.schoolmanagement.faculty.model.Faculties;
import com.telu.schoolmanagement.faculty.repository.FacultyRepository;
import com.telu.schoolmanagement.program.dto.ProgramRequestDTO;
import com.telu.schoolmanagement.program.dto.ProgramResponseDTO;
import com.telu.schoolmanagement.program.mapper.ProgramMapper;
import com.telu.schoolmanagement.program.model.Programs;
import com.telu.schoolmanagement.program.repository.ProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Autowired
    RedisCacheUtil redisCacheUtil;

    @Transactional
    public List<ProgramResponseDTO> getAllProgram() {
        List<ProgramResponseDTO> redisdata = redisCacheUtil.getCachedList(
                AppConstant.REDIS_GET_ALL_PROGRAM_LIST,
                new TypeReference<List<ProgramResponseDTO>>() {}
        );

        System.out.println("Data di redis :" + redisdata);

        //if data available
        if(redisdata != null) return redisdata;

        //if no data in redis
        System.out.println(" Call data form DB");
        var result = programRepository.findAll().stream()
                .map(ProgramMapper::toDTO)
                .toList();

        redisCacheUtil.cacheValue(AppConstant.REDIS_GET_ALL_PROGRAM_LIST, result);
        return result;

    }

    @Transactional
    public ProgramResponseDTO getProgramById(Long id) {

        String redisKey = "program::" + id;

        ProgramResponseDTO redisdata = redisCacheUtil.getCachedList(
                AppConstant.REDIS_GET_ALL_PROGRAM_LIST,
                new TypeReference<ProgramResponseDTO>() {}
        );System.out.println("Data di redis :" + redisdata);

        //if data avaible
        if(redisdata != null) return redisdata;

        //if no data in redis
        System.out.println(" Call data form DB");
        var result = ProgramMapper.toDTO(programRepository.findById(id).orElseThrow());
        redisCacheUtil.cacheValue(redisKey, result);
        return result;
    }

    @Transactional
    public List<ProgramResponseDTO> getProgramByName(String name) {
        return programRepository.findByNameIgnoreCaseContaining(name).stream()
                .map(ProgramMapper::toDTO)
                .toList();
    }

    @Transactional
    public void createProgram(ProgramRequestDTO request) {

        deleteAllProgramCache();
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

    @Transactional
    public void deleteProgram(Long id) {
        deleteAllProgramCache(id);
        if (!programRepository.existsById(id)) {
            throw new EntityNotFoundException("Program with id " + id + " doesn't exist");
        }

        programRepository.deleteById(id);
    }

    @Transactional
    public void updateProgram(Long id, ProgramRequestDTO request) {
        deleteAllProgramCache(id);
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

    /**
     * Delete all Program list cache.
     */
    public void deleteAllProgramCache() {
        redisCacheUtil.deleteCache(AppConstant.REDIS_GET_ALL_PROGRAM_LIST);
    }

    /**
     * Delete all Program list cache and specific Program by ID.
     * @param id the ID of the Program to delete from cache
     */
    public void deleteAllProgramCache(Long id) {
        deleteAllProgramCache();
        redisCacheUtil.deleteCache("program::" + id);
    }
}
