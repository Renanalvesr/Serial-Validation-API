package com.validation.serial_validation.service;

import com.validation.serial_validation.entity.Serial;
import com.validation.serial_validation.repository.SerialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for handling business logic related to Serial entities.
 * This class interacts with the SerialRepository to perform CRUD operations
 * and can be used by controllers to manage serial records in the database.
 */
@Service  // Indicates that this class is a service, managed by the Spring container
public class SerialService {

    // Injecting the SerialRepository to interact with the database
    private final SerialRepository serialRepository;

    /**
     * Constructor for SerialService that performs dependency injection of SerialRepository.
     * @param serialRepository The SerialRepository to interact with the database.
     */
    @Autowired  // Spring will automatically inject the repository bean here
    public SerialService(SerialRepository serialRepository) {
        this.serialRepository = serialRepository;
    }

    /**
     * Retrieves all Serial records from the database.
     * @return A list of Serial objects.
     */
    public List<Serial> findAll() {
        // Using the SerialRepository to fetch all serials from the database
        return serialRepository.findAll();
    }

    /**
     * Retrieves a Serial record by its UUID (Unique Identifier).
     * @param id The UUID of the serial record to be retrieved.
     * @return An Optional containing the Serial object if found, otherwise empty.
     */
    public Optional<Serial> findById(UUID id) {
        // Using the repository to find the Serial by ID
        return serialRepository.findById(id);
    }

    /**
     * Saves a new or existing Serial record to the database.
     * @param serial The Serial entity to be saved or updated.
     * @return The saved Serial object.
     */
    public Serial save(Serial serial) {
        // Delegate the save operation to the repository
        return serialRepository.save(serial);
    }

    /**
     * Deletes a Serial record from the database by its UUID.
     * @param id The UUID of the Serial to be deleted.
     */
    public void deleteById(UUID id) {
        // Delegate the delete operation to the repository
        serialRepository.deleteById(id);
    }
}
