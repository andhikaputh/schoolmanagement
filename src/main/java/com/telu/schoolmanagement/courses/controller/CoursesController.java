package com.telu.schoolmanagement.courses.controller;

import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.courses.dto.CoursesResponseDTO;
import com.telu.schoolmanagement.courses.service.CoursesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @Operation(summary = "Get all courses")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CoursesResponseDTO>>> getAllCourses() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", coursesService.getAllCourses()));
    }

    @Operation(summary = "Get course by id")
    @GetMapping("/id={id}")
    public ResponseEntity<ApiResponse<CoursesResponseDTO>> getCourseById(@PathVariable Long id) {
        CoursesResponseDTO course = coursesService.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Success", course));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>(false, "Course not found", null));
        }
    }
}
