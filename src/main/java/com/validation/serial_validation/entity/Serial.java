package com.validation.serial_validation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Entity class representing a Serial record in the database.
 * This class is mapped to the 'serial' table in the database using JPA annotations.
 * It also uses Lombok annotations to automatically generate common methods like getters, setters, etc.
 */
@Entity  // Marks this class as a JPA entity
@Table(name = "serial")  // Maps the class to the 'serial' table in the database
@Data  // Lombok annotation to generate getters, setters, toString, equals, hashCode methods
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor  // Lombok annotation to generate a constructor with all arguments
public class Serial {

    /**
     * Unique identifier for the Serial record.
     * It is automatically generated using UUID strategy to ensure uniqueness.
     */
    @Id  // Marks this field as the primary key for the entity
    @GeneratedValue(strategy = GenerationType.UUID)  // Generates the UUID automatically
    private UUID serialNumber;

    /**
     * The name of the Serial. This field is required (non-null) and has a maximum length of 100 characters.
     */
    @Column(nullable = false, length = 100)  // Maps this field to a column with the specified constraints
    private String name;

    /**
     * A description of the Serial. This field is required (non-null) and has a maximum length of 255 characters.
     */
    @Column(nullable = false, length = 255)  // Maps this field to a column with the specified constraints
    private String description;

    /**
     * The status of the Serial, represented by an enum.
     * This field is required (non-null) and its value is stored as a string (using EnumType.STRING).
     */
    @Enumerated(EnumType.STRING)  // Maps the enum to a column where the string value of the enum will be stored
    @Column(nullable = false, length = 50)  // Maps this field to a column with the specified constraints
    private Status status;

    /**
     * Enum representing the possible statuses of a Serial.
     * The values of this enum are mapped to the 'status' column in the 'serial' table.
     */
    public enum Status {
        WAREHOUSE,  // Serial is in the warehouse
        IN_TRANSIT,  // Serial is in transit
        DELIVERED  // Serial has been delivered
    }
}
