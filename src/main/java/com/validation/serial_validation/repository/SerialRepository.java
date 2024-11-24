package com.validation.serial_validation.repository;

import com.validation.serial_validation.entity.Serial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for accessing Serial entities from the database.
 * This interface extends JpaRepository, which provides CRUD operations
 * for the Serial entity, including saving, updating, deleting, and querying.
 * JpaRepository is a Spring Data interface that reduces boilerplate code
 * needed for data access logic.
 */
@Repository  // Marks this interface as a Spring Data repository bean
public interface SerialRepository extends JpaRepository<Serial, UUID> {

    // No need to define any methods here unless custom queries are needed.
    // JpaRepository already provides basic methods like:
    // - findAll()        // Retrieve all Serial records
    // - findById()       // Retrieve a Serial by its UUID (Primary Key)
    // - save()           // Save a Serial record
    // - deleteById()     // Delete a Serial by its UUID
}