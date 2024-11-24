package com.validation.serial_validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the Spring Boot application.
 * This class contains the main method, which serves as the entry point for the application.
 * The @SpringBootApplication annotation enables Spring Boot's auto-configuration and component scanning.
 */
@SpringBootApplication  // Indicates that this is a Spring Boot application
public class SerialValidationApplication {

	/**
	 * The main method that runs the Spring Boot application.
	 * It launches the Spring context and the embedded web server (like Tomcat, if used).
	 * @param args Command-line arguments passed to the application (usually not needed in typical web apps).
	 */
	public static void main(String[] args) {
		// This line boots up the Spring Boot application and starts the embedded server
		SpringApplication.run(SerialValidationApplication.class, args);
	}
}
