package com.telu.schoolmanagement.students.controller;

import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.students.dto.StudentRequestDTO;
import com.telu.schoolmanagement.students.dto.StudentResponseDTO;
import com.telu.schoolmanagement.students.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Student controller", description = "CRUD for students")
@RestController
@RequestMapping("api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Operation(summary = "Get all students",description = "Showing all students data")
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponseDTO>>> getAllStudents() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Success", studentService.getAllStudents())
        );
    }
    @Operation(summary = "Search students by NIM", description = "Find students by NIM")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StudentResponseDTO>>> searchStudentsByNim(
            @RequestParam String nim) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Success", studentService.searchStudentsByNim(nim))
        );
    }

    @Operation(summary = "Create new student", description = "Add new student")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createStudent(
            @RequestBody @Valid StudentRequestDTO request,
            @RequestParam Long userId) {
        studentService.createStudent(request, userId);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Student created successfully", request.toString())
        );
    }

    @Operation(summary = "Update student", description = "Update student by NIM")
    @PutMapping("/{nim}")
    public ResponseEntity<ApiResponse<String>> updateStudent(
            @PathVariable String nim,
            @RequestBody @Valid StudentRequestDTO request) {
        studentService.updateStudent(nim, request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Student updated successfully", request.toString())
        );
    }

    @Operation(summary = "Delete student", description = "Delete student by NIM")
    @DeleteMapping("/{nim}")
    public ResponseEntity<ApiResponse<String>> deleteStudentByNim(@PathVariable String nim) {
        studentService.deleteStudentByNim(nim);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Student deleted successfully", null)
        );
    }
}
