package com.validation.serial_validation.errors.ErrorResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A class that represents the structure of an error response sent to the client.
 * This is used when handling exceptions and returning structured error information.
 * The response includes an error code, a message describing the error, and the corresponding HTTP status.
 */
@Getter  // Lombok annotation to generate getter methods for all fields
@AllArgsConstructor  // Lombok annotation to generate a constructor with all fields as parameters
public class ErrorResponse {

    /**
     * A unique error code to represent the specific type of error.
     * This can be used by the client to identify and handle the error appropriately.
     */
    private String errorCode;

    /**
     * A human-readable message that describes the error.
     * This provides more context to the client about what went wrong.
     */
    private String errorMessage;

    /**
     * The HTTP status code associated with the error.
     * This can be used by the client to understand the nature of the error (e.g., 404 for Not Found, 500 for Internal Server Error).
     */
    private int httpStatus;
}
