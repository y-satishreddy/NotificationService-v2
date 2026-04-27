package com.skylimit.Skylimit.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"timestamp","status","message","errors"})
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private Map<String,String> errors;
    public ApiError(LocalDateTime timestamp, int status, String messsage){
        this.timestamp = timestamp;
        this.status = status;
        this.message = messsage;
    }
}
