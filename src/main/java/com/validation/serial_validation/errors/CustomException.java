package com.validation.serial_validation.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Custom exception class for handling specific error cases in the application.
 * This class extends RuntimeException and is designed to be used for throwing
 * exceptions that include an error code, error message, and HTTP status.
 * It's typically used for business logic errors or validation failures.
 */
@Getter  // Lombok annotation to generate getter methods for all fields
@Setter  // Lombok annotation to generate setter methods for all fields
@AllArgsConstructor  // Lombok annotation to generate a constructor with all fields as parameters
public class CustomException extends RuntimeException {

    // Error code representing the specific error type (e.g., "4004" for serial not found)
    private String errorCode;

    // A descriptive message explaining the error (e.g., "Serial number not found")
    private String errorMessage;

    // HTTP status code associated with the error (e.g., 404 for not found)
    private int httpStatus;
}
