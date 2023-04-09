package com.example.examserverportal.helper;

import com.example.examserverportal.model.exam.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobelExceptionHnadler {

    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHnadler(IdNotFound ex){
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ApiResponse> NotFound(NotFound ex){
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}

