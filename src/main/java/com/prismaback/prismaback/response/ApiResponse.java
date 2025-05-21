package com.prismaback.prismaback.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private String message;
    private T data;
    private Integer status;
    private LocalDateTime timestamp;
}
