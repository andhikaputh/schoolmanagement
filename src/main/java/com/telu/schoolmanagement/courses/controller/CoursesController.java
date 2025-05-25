package com.telu.schoolmanagement.courses.controller;

import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.courses.dto.CoursesRequestDTO;
import com.telu.schoolmanagement.courses.dto.CoursesResponseDTO;
import com.telu.schoolmanagement.courses.service.CoursesService;
import com.telu.schoolmanagement.common.request.CreateGroup;
import com.telu.schoolmanagement.common.request.UpdateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO handle createdBy and updatedBy by login user
//TODO check user role access before allow to create, update, delete course
@Tag(name = "Courses Controller", description = "Endpoints for managing courses (CRUD operations)")
@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    //TODO dirty code, need to clean up
    @Operation(summary = "Get courses")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CoursesResponseDTO>>> findCourses(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        if (id == null && name == null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "success", coursesService.getAllCourses()));
        }
        //TODO how cache this?
        //TODO how to handle if both id and name are provided
        List<CoursesResponseDTO> courses = coursesService.searchCourses(id, name);
        if (courses != null && !courses.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "success", courses));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>(false, "Course not found", null));
        }
    }

    @Operation(summary = "Create a course")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createCourse(@RequestBody @Validated(CreateGroup.class) CoursesRequestDTO requestDTO){
        coursesService.createCourse(requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "Success add new course"));
    }

    @Operation(summary = "Update a course")
    @PutMapping
    public ResponseEntity<ApiResponse<String>> updateCourse(@RequestParam Long id, @RequestBody @Validated(UpdateGroup.class) CoursesRequestDTO requestDTO){
        coursesService.updateCourse(id, requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "Success update a course"));
    }

    @Operation(summary = "Delete a course")
    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteCourse(@RequestParam Long id){
        //TODO add error handler
        coursesService.deleteCourse(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "Success delete a course"));
    }
}
