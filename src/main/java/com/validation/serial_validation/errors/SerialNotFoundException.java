package com.validation.serial_validation.errors;

/**
 * Custom exception class for handling cases where a serial is not found.
 * This exception extends CustomException to provide a specific error code,
 * message, and HTTP status related to the "serial not found" error.
 */
public class SerialNotFoundException extends CustomException {

    /**
     * Constructor for SerialNotFoundException.
     * This initializes the exception with a specific error code, message, and HTTP status.
     * @param errorCode A unique code representing the error (e.g., "4004").
     * @param errorMessage A message describing the error (e.g., "Serial not found").
     * @param httpStatus The HTTP status code that corresponds to the error (e.g., 404 for "Not Found").
     */
    public SerialNotFoundException(String errorCode, String errorMessage, int httpStatus) {
        // Pass the parameters to the constructor of the parent class (CustomException)
        super(errorCode, errorMessage, httpStatus);
    }
}
