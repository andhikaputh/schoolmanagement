package com.telu.schoolmanagement.program.controller;


import com.telu.schoolmanagement.program.dto.ProgramRequestDTO;
import com.telu.schoolmanagement.program.dto.ProgramResponseDTO;
import com.telu.schoolmanagement.program.service.ProgramService;
import com.telu.schoolmanagement.common.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Program Controller", description = "Controller for Program")
@RestController
@RequestMapping("api/program")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @Operation(summary = "Get All Program", description = "Show All Program Registered")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProgramResponseDTO>>> getAllProgram(){
        List<ProgramResponseDTO> jurusanResponseDTO = programService.getAllProgram();
        return ResponseEntity.ok(new ApiResponse<>(true, "success", jurusanResponseDTO));
    }

    @Operation(summary = "Get Program by Id", description = "Find Program by Id")
    @GetMapping("/id={id}")
    public ResponseEntity<ApiResponse<ProgramResponseDTO>> getProgramById(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse<>(true, "success", programService.getProgramById(id)));
    }

    @Operation(summary = "Get Program by Name", description = "Find Program by Name")
    @GetMapping("/name={name}")
    public ResponseEntity<ApiResponse<List<ProgramResponseDTO>>> getProgramByName(@PathVariable String name){
        return ResponseEntity.ok(new ApiResponse<>(true, "success", programService.getProgramByName(name)));
    }

    @Operation(summary = "Create a New Program", description = "Adding New Program.")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createProgram(@RequestBody @Valid ProgramRequestDTO request) {
        programService.createProgram(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", request.toString()));
    }

    @Operation(summary = "Update a Program", description = "Updating a Program.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateProgram(@PathVariable Long id,@RequestBody @Valid ProgramRequestDTO request) {
        programService.updateProgram(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "success update program by id : " + id, request.toString()));
    }

    @Operation(summary = "Delete a Program", description = "Delete a Program.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteJurusanById(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success delete program with id : " + id, null));
    }

}
