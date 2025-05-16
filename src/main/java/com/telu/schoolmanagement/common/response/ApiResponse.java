package com.telu.schoolmanagement.common.response;

import lombok.*;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
}
