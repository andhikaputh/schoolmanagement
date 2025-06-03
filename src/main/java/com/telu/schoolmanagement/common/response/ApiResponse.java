package com.telu.schoolmanagement.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Optional;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private PaginationResponse pagination;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(boolean success, String message, T data, PaginationResponse pagination) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.pagination = pagination;
    }
}
