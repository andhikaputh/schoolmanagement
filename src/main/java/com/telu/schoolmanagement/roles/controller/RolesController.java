package com.telu.schoolmanagement.roles.controller;


import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.roles.dto.RolesRequestDTO;
import com.telu.schoolmanagement.roles.dto.RolesResponseDTO;
import com.telu.schoolmanagement.roles.service.RolesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Roles Controller", description = "CRUD for Roles")
@RestController
@RequestMapping("api/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RolesResponseDTO>>> getAllRoles(){
        return ResponseEntity.ok(new ApiResponse<>(true,"success", rolesService.getAllRoles()));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<ApiResponse<RolesResponseDTO>> getRolesById(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse<>(true, "success", rolesService.getRolesById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createRoles(@RequestBody @Valid RolesRequestDTO request){
        rolesService.createRoles(request);
        return ResponseEntity.ok(new ApiResponse<>(true,"success","success create new data Roles"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateRoles(@PathVariable Long id, @RequestBody @Valid RolesRequestDTO request){
        rolesService.updateRoles(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "succes", "Succes update roles with id" + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteRoles(@PathVariable Long id){
        rolesService.deleteRolesById(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Succes", "Succes delete with id : " + id));
    }

}
