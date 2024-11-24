package com.validation.serial_validation.controller;

import com.validation.serial_validation.entity.Serial;
import com.validation.serial_validation.errors.SerialNotFoundException;
import com.validation.serial_validation.service.SerialService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@OpenAPIDefinition(
        info = @Info(
                title = "Serial Validation API",
                version = "v1",
                description = "API for serial number validation"
        )
)

@RestController
@RequestMapping("/serials")
public class SerialController {
    // Injecting the SerialService, which contains the business logic
    private final SerialService serialService;

    @Autowired  // Constructor-based dependency injection
    public SerialController(SerialService serialService) {
        this.serialService = serialService;
    }

    /**
     * Endpoint to retrieve all serial records from the database.
     *
     * @return List of Serial objects in JSON format
     */
    @CrossOrigin(origins = "http://localhost:4200")  // Allow cross-origin requests from frontend (Angular)
    @GetMapping  // Mapping HTTP GET requests to this method
    public List<Serial> getAllSerials() {
        return serialService.findAll();  // Calling service layer to fetch all serial records
    }

    /**
     * Endpoint to retrieve a specific serial by its tracking number (UUID).
     *
     * @param id The UUID of the serial to search for
     * @return ResponseEntity containing the serial details or a 404 if not found
     */
    @CrossOrigin(origins = "http://localhost:4200")  // Allow cross-origin requests from frontend (Angular)
    @GetMapping("/trackingNumber={id}")  // Custom path variable in the URL
    public ResponseEntity<Serial> getSerialById(@PathVariable UUID id) {
        // Try to find the serial by ID using the service layer
        Optional<Serial> serial = serialService.findById(id);

        // If serial is not found, throw a custom exception with 404 status code
        if (serial.isEmpty()) {
            throw new SerialNotFoundException("4004", "Serial not found.", 404);
        }

        // Return the found serial as HTTP 200 OK or a 404 response if not found
        return serial.map(ResponseEntity::ok)  // If found, return OK status with serial data
                .orElse(ResponseEntity.notFound().build());  // If not found, return 404
    }
}