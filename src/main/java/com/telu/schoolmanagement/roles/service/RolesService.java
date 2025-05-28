package com.telu.schoolmanagement.roles.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.telu.schoolmanagement.common.constant.AppConstant;
import com.telu.schoolmanagement.common.redis.RedisCacheUtil;
import com.telu.schoolmanagement.roles.dto.RolesRequestDTO;
import com.telu.schoolmanagement.roles.dto.RolesResponseDTO;
import com.telu.schoolmanagement.roles.mapper.RolesMapper;
import com.telu.schoolmanagement.roles.model.Roles;
import com.telu.schoolmanagement.roles.repository.RolesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    RedisCacheUtil redisCacheUtil;

    public List<RolesResponseDTO> getAllRoles(){
        List<RolesResponseDTO> redisData = redisCacheUtil.getCachedList(
                        AppConstant.REDIS_GETALL_ROLES,
                        new TypeReference<List<RolesResponseDTO>>() {}
                );
                System.out.println("Roles from redis " + redisData);
                if (redisData != null) return redisData;

                System.out.println("Call data from DB");
                var result = rolesRepository.findAll().stream()
                            .map(RolesMapper::toDTO)
                            .toList();

                redisCacheUtil.cacheValue(AppConstant.REDIS_GETALL_ROLES,result);
                return result;
    }

    public RolesResponseDTO getRolesById(Long id){
        String redisKey = "roles::"+id;
        RolesResponseDTO redisData = redisCacheUtil.getCachedValue(
                AppConstant.REDIS_GETALL_ROLES,
                RolesResponseDTO.class
        );
        if (redisData != null) return redisData;

        System.out.println("call data from DB");
        RolesResponseDTO result = RolesMapper.toDTO(rolesRepository.findById(id).orElseThrow());
        redisCacheUtil.cacheValue(redisKey,result);

        return result;

    }

    public void createRoles(RolesRequestDTO request) {
        deleteAllRolesCache();
        
        Roles roles = Roles.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdBy(request.getCreatedBy())
                .updatedBy(request.getUpdateBy())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .build();

        rolesRepository.save(roles);
    }

    public void deleteRolesById(Long id){
        deleteAllRolesIDCache(id);

        if (!rolesRepository.existsById(id)){
            throw new EntityNotFoundException(" Roles dengan ID " + id + " tidak ditemukan");
        }
        rolesRepository.deleteById(id);
    }

    public void updateRoles(Long id, RolesRequestDTO requestDTO){
        deleteAllRolesIDCache(id);

        Roles newRoles = rolesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Roles dengan ID " + id + "tidak ditemukan."));

        newRoles.setName(requestDTO.getName());
        rolesRepository.save(newRoles);
    }

    public void deleteAllRolesCache(){
        redisCacheUtil.deleteCache(AppConstant.REDIS_GETALL_ROLES);
    }

    public void deleteAllRolesIDCache(Long id){
        deleteAllRolesCache();
        redisCacheUtil.deleteCache("roles::" + id);
    }
}
