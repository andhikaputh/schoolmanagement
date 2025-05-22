package com.telu.schoolmanagement.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Nip Users cannot be blank")
    @Size(max = 15,  message = "Max Nip is 15 characters")
    private String nip;

    @NotBlank(message = "Password Users cannot be blank")
    private String password;
}
