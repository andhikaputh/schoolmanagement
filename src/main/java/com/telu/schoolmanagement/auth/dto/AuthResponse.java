package com.telu.schoolmanagement.auth.dto;

import com.telu.schoolmanagement.users.dto.UsersResponseDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private UsersResponseDTO user;
}