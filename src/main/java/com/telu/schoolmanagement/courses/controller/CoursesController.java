package com.telu.schoolmanagement.courses.controller;

import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.courses.dto.CoursesRequestDTO;
import com.telu.schoolmanagement.courses.dto.CoursesResponseDTO;
import com.telu.schoolmanagement.courses.service.CoursesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Courses", description = "CRUD for Courses")
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

    @Operation(summary = "Search course by id or name")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CoursesResponseDTO>>> searchCourses(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        List<CoursesResponseDTO> courses = coursesService.searchCourses(id, name);
        if (courses != null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Success", courses));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>(false, "Course not found", null));
        }
    }

    @Operation(summary = "Create a course")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createCourse(@RequestBody CoursesRequestDTO requestDTO){
        //TODO add error handler
        coursesService.createCourse(requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", "Success add new course"));
    }

    @Operation(summary = "Update a course")
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateCourse(@RequestParam Long id, @RequestBody CoursesRequestDTO requestDTO){
        //TODO add error handler
        coursesService.updateCourse(id, requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", "Success update a course"));
    }

    @Operation(summary = "Delete a course")
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@RequestParam Long id){
        //TODO add error handler
        coursesService.deleteCourse(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", "Success delete a course"));
    }
}
