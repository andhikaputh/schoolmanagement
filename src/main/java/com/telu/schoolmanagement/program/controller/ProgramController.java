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

    @Operation(summary = "Get all program", description = "Show all program registered")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProgramResponseDTO>>> getAllProgram(){
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", programService.getAllProgram()));
    }

    @Operation(summary = "Get program by id", description = "find program by id")
    @GetMapping("/id={id}")
    public ResponseEntity<ApiResponse<ProgramResponseDTO>> getProgramById(@PathVariable Long id){
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", programService.getProgramById(id)));
    }

    @Operation(summary = "Get program by name", description = "find program by name")
    @GetMapping("/name={name}")
    public ResponseEntity<ApiResponse<List<ProgramResponseDTO>>> getProgramByName(@PathVariable String name){
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", programService.getProgramByName(name)));
    }

    @Operation(summary = "Create a new program", description = "Adding new program.")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createProgram(@RequestBody @Valid ProgramRequestDTO request) {
        programService.createProgram(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", request.toString()));
    }

    @Operation(summary = "Update a program", description = "Updating a program.")
    @PutMapping
    public ResponseEntity<ApiResponse<String>> updateProgram(@RequestBody Long id, @Valid ProgramRequestDTO request) {
        programService.updateProgram(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "success update program by id : " + id, request.toString()));
    }

    @Operation(summary = "delete a program", description = "delete a program.")
    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteJurusanById(@RequestBody Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success delete program with id : " + id, null));
    }

}
