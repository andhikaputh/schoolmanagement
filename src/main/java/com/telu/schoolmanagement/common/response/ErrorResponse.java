package com.telu.schoolmanagement.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private boolean success;
    private String message;

    private LocalDateTime timeStamp;
    private String path;
}

