package com.telu.schoolmanagement.grades.controller;

import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.grades.dto.GradeRequestDTO;
import com.telu.schoolmanagement.grades.dto.GradeResponseDTO;
import com.telu.schoolmanagement.grades.service.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grade Controller", description = "CRUD for grade")
@RestController
@RequestMapping("api/grade")
public class GradeController {
    @Autowired
    GradeService gradeService;

    @Operation(summary = "Get all grades", description = "Show all grade data")
    @GetMapping
    public ResponseEntity<ApiResponse<List<GradeResponseDTO>>> getAllGrade(){
        return ResponseEntity.ok(new ApiResponse<>(true, "success",gradeService.getAllGrade()));
    }

    @Operation(summary = "Get grades by id", description = "Show grade data by id from user")
    @GetMapping("/id={id}")
    public ResponseEntity<ApiResponse<GradeResponseDTO>> getGradeById(Long id){
        return ResponseEntity.ok(new ApiResponse<>(true, "success",gradeService.getGradeById(id)));
    }

    @Operation(summary = "create grades", description = "create new grades")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createGrade(@RequestBody @Valid GradeRequestDTO requestDTO){
        gradeService.createGrade(requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "success create grade"));
    }
    @Operation(summary = "update grades", description = "update a grades")
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<String>> updateGrade(@PathVariable Long id,@RequestBody @Valid GradeRequestDTO requestDTO){
        gradeService.updateGrade(id,requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "success update grade with id : " + id, null ));
    }
    @Operation(summary = "delete grades", description = "delete a grades")
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<String>> deleteGrade(@PathVariable Long id){
        gradeService.deleteGrade(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success delete grade with id " + id , null));
    }

}
