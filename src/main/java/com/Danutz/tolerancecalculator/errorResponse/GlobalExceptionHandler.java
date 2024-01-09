package com.Danutz.tolerancecalculator.errorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ex.getErrorCode());
        errorResponse.setErrorMessage(ex.getMessage());
        // Set additional fields if needed

        HttpStatus status = determineHttpStatus(ex); // Custom method to determine status
        return new ResponseEntity<>(errorResponse, status);
    }

    private HttpStatus determineHttpStatus(CustomException ex) {
        // You can customize this logic based on the type of CustomException
        // For example, return HttpStatus.NOT_FOUND for a specific scenario

        // Default to HttpStatus.BAD_REQUEST if no specific logic is implemented
        return HttpStatus.BAD_REQUEST;
    }

    // Add other exception handlers for different types of exceptions if needed
}

