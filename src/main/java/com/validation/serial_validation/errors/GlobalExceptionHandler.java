package com.validation.serial_validation.errors;

import com.validation.serial_validation.errors.ErrorResponse.ErrorResponse;
import com.validation.serial_validation.errors.SerialNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SerialNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSerialNotFound(SerialNotFoundException ex) {
        String errorMessage = ex.getMessage() != null ? ex.getMessage() : "Serial not found";
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), errorMessage, ex.getHttpStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("500", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
