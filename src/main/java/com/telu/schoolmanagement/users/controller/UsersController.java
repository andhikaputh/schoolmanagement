package com.telu.schoolmanagement.users.controller;

import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.users.dto.UsersRequestDTO;
import com.telu.schoolmanagement.users.dto.UsersResponseDTO;
import com.telu.schoolmanagement.users.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// Swagger URL
@Tag(name = "Users Controller", description = "CRUD for all Users")
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Operation(summary = "Get all Users Data")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UsersResponseDTO>>> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", usersService.getAllUsers()));
    }

    @Operation(summary = "Get all Users Data with Pagination")
    @GetMapping("/paginated")
    public ResponseEntity<ApiResponse<List<UsersResponseDTO>>> getAllUsersWithPagination(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(usersService.getAllUsersWithPagination(page, size));
    }

    @Operation(summary = "Get Users by Id")
    @GetMapping("/id={id}")
    public ResponseEntity<ApiResponse<UsersResponseDTO>> getIdUsers(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", usersService.getUsersById(id)));
    }

    @Operation(summary = "Get Users by Nip")
    @GetMapping("/nip={nip}")
    public ResponseEntity<ApiResponse<UsersResponseDTO>> getUsersByNip(@PathVariable String nip) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", usersService.getUsersByNip(nip)));
    }
    @Operation(summary = "Get Users by Name")
    @GetMapping("/name={name}")
    public ResponseEntity<ApiResponse<List<UsersResponseDTO>>> getUsersByName(@PathVariable String name) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", usersService.getUserByName(name)));
    }

    @Operation(summary = "Get Users if active")
    @GetMapping("/act={act}")
    public ResponseEntity<ApiResponse<List<UsersResponseDTO>>> getUsersByIsAct(@PathVariable Boolean act) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", usersService.getUserByIsAct(act)));
    }

    @Operation(summary = "Creating User")
    @PostMapping("")
    public ResponseEntity<ApiResponse<String>> createUsers(@RequestBody @Valid UsersRequestDTO req) {
        usersService.createUsers(req);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "Success creating User"));
    }

    @Operation(summary = "Updating user by Id")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateUsers(@RequestBody @Valid UsersRequestDTO req, @PathVariable Long id) {
        usersService.updateUsers(id, req);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "Success Updating User"));
    }

    @Operation(summary = "Deleting user by Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUsers(@PathVariable Long id) {
        usersService.deleteUsers(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success", "Success Deleting User"));
    }
}
