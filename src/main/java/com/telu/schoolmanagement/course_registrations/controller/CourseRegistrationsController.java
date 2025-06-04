package com.telu.schoolmanagement.course_registrations.controller;

import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.course_registrations.dto.CourseRegistrationsRequestDTO;
import com.telu.schoolmanagement.course_registrations.dto.CourseRegistrationsResponseDTO;
import com.telu.schoolmanagement.course_registrations.service.CourseRegistrationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Course Registrations Controller", description = "CRUD for course registrations")
@RestController
@RequestMapping("/api/course-registrations")
public class CourseRegistrationsController {

    @Autowired
    private CourseRegistrationsService courseRegistrationsService;

    @Operation(summary = "Get all course registrations")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseRegistrationsResponseDTO>>> getCourseRegistrations() {
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "success",
                courseRegistrationsService.getAllCourseRegistrations())
        );
    }

    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseRegistrationsResponseDTO>> getCourseRegistrationById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "success",
                courseRegistrationsService.getCourseRegistrationById(id))
        );
    }

    @Operation(summary = "Create a course registration")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createCourseRegistration(@RequestBody CourseRegistrationsRequestDTO request) {
        courseRegistrationsService.createCourseRegistration(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "Course registration created successfully"));
    }

    @Operation(summary = "Update a course registration")
    @PutMapping
    public ResponseEntity<ApiResponse<String>> updateCourseRegistration
    (@RequestParam Long id, @RequestBody CourseRegistrationsRequestDTO request) {
        courseRegistrationsService.updateCourseRegistration(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "Course registration updated successfully"));
    }

    @Operation(summary = "Delete a course registration")
    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteCourseRegistration(Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "Course registration deleted successfully"));
    }
}
