package com.telu.schoolmanagement.users.controller;

import com.telu.schoolmanagement.common.response.ApiResponse;
import com.telu.schoolmanagement.users.dto.UsersResponseDTO;
import com.telu.schoolmanagement.users.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

// Swagger URL
@Tag(name = "Users Controller", description = "CRUD for all Users ( Create Update Delete havent done it yet )")
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Tag(name = "Get all Users Data")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UsersResponseDTO>>> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", usersService.getAllUsers()));
    }

//    @Tag(name = "Get Users by Id")
//    @GetMapping("/id={id}")
//    public ResponseEntity<ApiResponse<UsersResponseDTO>> getIdUsers(@PathVariable Long id) {
//        return ResponseEntity.ok(new ApiResponse<>(true, "success", usersService.getUsersById(id)));
//    }
//
//    @Tag(name = "Get Users by Nip")
//    @GetMapping("/nip={nip}")
//    public ResponseEntity<ApiResponse<UsersResponseDTO>> getUsersByNip(@PathVariable String nip) {
//        return ResponseEntity.ok(new ApiResponse<>(true, "success", usersService.getUsersByNip(nip)));
//    }
//    @Tag(name = "Get Users by Name")
//    @GetMapping("/name={name}")
//    public ResponseEntity<ApiResponse<List<UsersResponseDTO>>> getUsersByName(@PathVariable String name) {
//        return ResponseEntity.ok(new ApiResponse<>(true, "success", usersService.getUserByName(name)));
//    }
//
//    @Tag(name = "Get Users if active")
//    @GetMapping("/act={act}")
//    public ResponseEntity<ApiResponse<List<UsersResponseDTO>>> getUsersByIsAct(@PathVariable Boolean act) {
//        return ResponseEntity.ok(new ApiResponse<>(true, "success", usersService.getUserByIsAct(act)));
//    }
//
//    @Tag(name = "Get Users Graduated date")
//    @GetMapping("/grad={grad}")
//    public ResponseEntity<ApiResponse<List<UsersResponseDTO>>> getUsersByGrad(@PathVariable("grad") String grad) {
//        LocalDate date = LocalDate.parse(grad);
//        return ResponseEntity.ok(new ApiResponse<>(true, "success", usersService.getUsersByGrad(date)));
//    }

}
