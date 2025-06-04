package com.telu.schoolmanagement.grades.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.telu.schoolmanagement.common.constant.AppConstant;
import com.telu.schoolmanagement.common.redis.RedisCacheUtil;
import com.telu.schoolmanagement.grades.dto.GradeRequestDTO;
import com.telu.schoolmanagement.grades.dto.GradeResponseDTO;
import com.telu.schoolmanagement.grades.mapper.GradeMapper;
import com.telu.schoolmanagement.grades.model.Grades;
import com.telu.schoolmanagement.grades.repository.GradeRepository;
import com.telu.schoolmanagement.users.model.Users;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private RedisCacheUtil redisCacheUtil;



    public List<GradeResponseDTO> getAllGrade() {
        List<GradeResponseDTO> redisData = redisCacheUtil.getCachedList(
                AppConstant.REDIS_GETALL_GRADE, new TypeReference<List<GradeResponseDTO>>() {}
        );
        System.out.println("Data di redis : " + redisData);

        //if data available in redis
        if(redisData != null) return redisData;

        //if no data in redis
        System.out.println("Call data from DB");
        var result = gradeRepository.findAll().stream()
                .map(GradeMapper::toDTO)
                .toList();

        redisCacheUtil.cacheValue(AppConstant.REDIS_GETALL_GRADE,result);
        return result;
    }


    public GradeResponseDTO getGradeById(Long id){
        String redisKey = "grade::" + id;
        GradeResponseDTO redisData = redisCacheUtil.getCachedValue(
                redisKey, GradeResponseDTO.class
        );

        if (redisData != null) return redisData;

        System.out.println("Call data from DB");
        GradeResponseDTO result = GradeMapper.toDTO(gradeRepository.findById(id).orElseThrow());
        redisCacheUtil.cacheValue(redisKey, result);
        return result;
    }

    public void createGrade(GradeRequestDTO requestDTO){
        deleteAllGradeCache();
        //krs

        Grades grades = Grades.builder()
                .krsId(requestDTO.getKrsId()) //TODO validate after krs already created
                .assignmentScore(requestDTO.getAssignmentScore())
                .midtermScore(requestDTO.getMidtermScore())
                .finalGrade(requestDTO.getFinalGrade())
                .finalScore(requestDTO.getFinalScore())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .createdBy(requestDTO.getCreatedBy())
                .updatedBy(requestDTO.getUpdatedBy())
                .build();
        gradeRepository.save(grades);
    }

    public void deleteGrade(Long id){
        deleteGradeCache(id);

        if (!gradeRepository.existsById(id)){
            throw new EntityNotFoundException("Grade with id " + id + "doesn't exist");
        }
        gradeRepository.deleteById(id);
    }

    public void updateGrade(Long id, GradeRequestDTO requestDTO){
        deleteGradeCache(id);
        Grades newGrade = gradeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Grade with Id " + id + " doesn't exist"));
        // validate krs

        newGrade.setKrsId(requestDTO.getKrsId());
        newGrade.setAssignmentScore(requestDTO.getAssignmentScore());
        newGrade.setMidtermScore(requestDTO.getMidtermScore());
        newGrade.setFinalScore(requestDTO.getFinalScore());
        newGrade.setFinalGrade(requestDTO.getFinalGrade());
        newGrade.setUpdatedAt(requestDTO.getUpdatedAt());
        newGrade.setUpdatedBy(requestDTO.getUpdatedBy());

        gradeRepository.save(newGrade);
    }

    public void deleteAllGradeCache() {
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETALL_GRADE);
    }

    private void deleteGradeCache(Long id) {
        deleteAllGradeCache();
        redisCacheUtil.deleteCache("grade::" + id);
    }
}
