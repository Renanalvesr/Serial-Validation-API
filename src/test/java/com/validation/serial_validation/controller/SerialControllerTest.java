package com.validation.serial_validation.controller;

import com.validation.serial_validation.entity.Serial;
import com.validation.serial_validation.service.SerialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SerialControllerTest {

    @InjectMocks
    private SerialController serialController;

    @Mock
    private SerialService serialService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(serialController).build();
    }

    @Test
    void getAllSerials_shouldReturnAllSerials() throws Exception {
        // Arrange
        Serial serial1 = new Serial(UUID.randomUUID(), "Serial1", "Description 1", Serial.Status.DELIVERED);
        Serial serial2 = new Serial(UUID.randomUUID(), "Serial2", "Description 2", Serial.Status.IN_TRANSIT);
        when(serialService.findAll()).thenReturn(Arrays.asList(serial1, serial2));

        // Act & Assert
        mockMvc.perform(get("/serials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))  // Validate two serials
                .andExpect(jsonPath("$[0].serialNumber").value(serial1.getSerialNumber().toString()))  // Correct field name (adjust if needed)
                .andExpect(jsonPath("$[1].serialNumber").value(serial2.getSerialNumber().toString()));  // Same here
    }

    @Test
    void getSerialById_shouldReturnSerial_whenFound() throws Exception {
        // Arrange
        UUID serialId = UUID.randomUUID();
        Serial serial = new Serial(serialId, "Serial1", "Description 1", Serial.Status.DELIVERED);
        when(serialService.findById(serialId)).thenReturn(Optional.of(serial));

        // Act & Assert
        mockMvc.perform(get("/serials/trackingNumber=" + serialId))  // Adjust URL path
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value(serialId.toString()));  // Validate the serial number
    }






}


