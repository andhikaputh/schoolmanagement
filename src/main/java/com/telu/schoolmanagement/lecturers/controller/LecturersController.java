package com.telu.schoolmanagement.lecturers.controller;

import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.lecturers.dto.LecturersRequestDTO;
import com.telu.schoolmanagement.lecturers.dto.LecturersResponseDTO;
import com.telu.schoolmanagement.lecturers.service.LecturersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Lecturers Controller", description = "CRUD for lecturers")
@RestController
@RequestMapping("api/lecturers")
public class LecturersController {
    @Autowired
    private LecturersService lecturersService;

    @Operation(summary = "Get all lecturers", description = "Showing all lecturers data")
    @GetMapping
    public ResponseEntity<ApiResponse<List<LecturersResponseDTO>>> getAllLecturers() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Success", lecturersService.getAllLecturers())
        );
    }

    @Operation(summary = "Search lecturers by NIDN", description = "Find lecturers by NIDN")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<LecturersResponseDTO>>> searchLecturersByNidn(
            @RequestParam String nidn) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Success", lecturersService.searchLecturersByNidn(nidn))
        );
    }

    @Operation(summary = "Create new lecturer", description = "Add new lecturer")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createLecturer(
            @RequestBody @Valid LecturersRequestDTO request,
            @RequestParam Long userId) {
        lecturersService.createLecturer(request, userId);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lecturer created successfully", request.toString())
        );
    }

    @Operation(summary = "Update lecturer", description = "Update lecturer by NIDN")
    @PutMapping("/{nidn}")
    public ResponseEntity<ApiResponse<String>> updateLecturer(
            @PathVariable String nidn,
            @RequestBody @Valid LecturersRequestDTO request) {
        lecturersService.updateLecturer(nidn, request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lecturer updated successfully", request.toString())
        );
    }

    @Operation(summary = "Delete lecturer", description = "Delete lecturer by NIDN")
    @DeleteMapping("/{nidn}")
    public ResponseEntity<ApiResponse<String>> deleteLecturerByNidn(@PathVariable String nidn) {
        lecturersService.deleteLecturerByNidn(nidn);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lecturer deleted successfully", null)
        );
    }
}