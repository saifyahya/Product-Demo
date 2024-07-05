package com.example.productDemo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter@Setter @NoArgsConstructor @AllArgsConstructor
public class ErrorResponseDto {
    private HttpStatus status;
    private String apiPath;
    private String message;
    private LocalDateTime dateTime;
}
