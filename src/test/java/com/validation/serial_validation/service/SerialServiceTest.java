package com.validation.serial_validation.service;

import com.validation.serial_validation.entity.Serial;
import com.validation.serial_validation.errors.SerialNotFoundException;
import com.validation.serial_validation.repository.SerialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SerialServiceTest {

    @InjectMocks  // Automatically injects the mocked SerialRepository into SerialService
    private SerialService serialService;

    @Mock  // Creates a mock instance of SerialRepository
    private SerialRepository serialRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes the mocks before each test
    }

    @Test
    void testFindAll() {
        // Arrange
        Serial serial1 = new Serial(UUID.randomUUID(), "Serial1", "Description 1", Serial.Status.DELIVERED);
        Serial serial2 = new Serial(UUID.randomUUID(), "Serial2", "Description 2", Serial.Status.IN_TRANSIT);
        when(serialRepository.findAll()).thenReturn(Arrays.asList(serial1, serial2));

        // Act
        var serials = serialService.findAll();

        // Assert
        assertNotNull(serials);
        assertEquals(2, serials.size());
        verify(serialRepository, times(1)).findAll();  // Verify that findAll() was called once
    }

    @Test
    void testFindById_SerialFound() {
        // Arrange
        UUID serialId = UUID.randomUUID();
        Serial serial = new Serial(serialId, "Serial1", "Description 1", Serial.Status.DELIVERED);
        when(serialRepository.findById(serialId)).thenReturn(Optional.of(serial));

        // Act
        Optional<Serial> foundSerial = serialService.findById(serialId);

        // Assert
        assertTrue(foundSerial.isPresent());
        assertEquals(serialId, foundSerial.get().getSerialNumber());
        verify(serialRepository, times(1)).findById(serialId);  // Verify findById was called once
    }

    @Test
    void testFindById_SerialNotFound() {
        // Arrange
        UUID serialId = UUID.randomUUID();
        when(serialRepository.findById(serialId)).thenReturn(Optional.empty());

        // Act
        Optional<Serial> foundSerial = serialService.findById(serialId);

        // Assert
        assertFalse(foundSerial.isPresent());
        verify(serialRepository, times(1)).findById(serialId);  // Verify findById was called once
    }

    @Test
    void testSave() {
        // Arrange
        Serial serial = new Serial(UUID.randomUUID(), "Serial1", "Description 1", Serial.Status.DELIVERED);
        when(serialRepository.save(serial)).thenReturn(serial);

        // Act
        Serial savedSerial = serialService.save(serial);

        // Assert
        assertNotNull(savedSerial);
        assertEquals("Serial1", savedSerial.getName());
        verify(serialRepository, times(1)).save(serial);  // Verify save was called once
    }

    @Test
    void testDeleteById() {
        // Arrange
        UUID serialId = UUID.randomUUID();
        doNothing().when(serialRepository).deleteById(serialId);  // Mock deleteById to do nothing

        // Act
        serialService.deleteById(serialId);

        // Assert
        verify(serialRepository, times(1)).deleteById(serialId);  // Verify deleteById was called once
    }

    @Test
    void testFindById_whenSerialNotFound() {
        // Given
        UUID nonExistingId = UUID.randomUUID();  // Simulate a non-existing Serial ID
        when(serialRepository.findById(nonExistingId)).thenReturn(Optional.empty());  // Mock the repository to return empty

        // When & Then
        assertThrows(SerialNotFoundException.class, () -> {
            serialService.findById(nonExistingId).orElseThrow(() -> new SerialNotFoundException("","Serial not found",404));
        });
    }
}
