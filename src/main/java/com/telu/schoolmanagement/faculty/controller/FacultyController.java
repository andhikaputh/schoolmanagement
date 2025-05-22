package com.telu.schoolmanagement.faculty.controller;
import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.faculty.dto.FacultyRequestDTO;
import com.telu.schoolmanagement.faculty.dto.FacultyResponseDTO;
import com.telu.schoolmanagement.faculty.service.FacultyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Faculty Controller", description = "CRUD for faculty")
@RestController
@RequestMapping("api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @Operation(summary = "Get all faculties", description = "Showing all faculty data")
    @GetMapping
    public ResponseEntity<ApiResponse<List<FacultyResponseDTO>>> getAllFaculty(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",facultyService.getAllFaculty()));

    }

    @Operation(summary = "Get faculty by ID", description = "Take all faculty data by ID.")
    @GetMapping("/id={id}")
    public ResponseEntity<ApiResponse<FacultyResponseDTO>> getJurusanById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", facultyService.getFacultyById(id)));

    }

    @Operation(summary = "Search faculty by name", description = "Find faculty by name")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<FacultyResponseDTO>>> getFacultyByName(@RequestParam String name) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", facultyService.getFacultyByName(name)));
    }

    @Operation(summary = "Create a new faculty", description = "Adding new faculty.")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createFaculty(@RequestBody @Valid FacultyRequestDTO request, @RequestParam Long userId) {
        facultyService.createFaculty(request,userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", request.toString()));
    }

    @Operation(summary = "Update faculty", description = "Change faculty by ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateFaculty(@PathVariable Long id, @RequestBody @Valid FacultyRequestDTO request) {
        facultyService.updateFaculty(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Update success with ID " + id, request.toString()));
    }

    @Operation(summary = "Delete faculty", description = "Delete faculty by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteJurusanById(@PathVariable Long id) {
        facultyService.deleteFacultyById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success delete with ID " + id, null));
    }
}
