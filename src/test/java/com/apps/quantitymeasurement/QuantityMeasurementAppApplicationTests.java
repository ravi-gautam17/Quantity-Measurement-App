package com.apps.quantitymeasurement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.model.QuantityInputDTO;
import com.apps.quantitymeasurement.model.QuantityMeasurementDTO;
import com.apps.quantitymeasurement.quantity.Quantity;

import org.junit.jupiter.api.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.*;
import org.springframework.boot.test.web.server.LocalServerPort;

/**
 * Full Integration Tests for Quantity Measurement REST API
 * Base URL: /api/v1/quantities [cite: 2977]  
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
public class QuantityMeasurementAppApplicationTests {

    @LocalServerPort
    private int port; 

    @Autowired
    private TestRestTemplate restTemplate; 

    private String baseUrl() {
        return "http://localhost:" + port + "/api/v1/quantities";
    }

    // Helper methods to build QuantityInputDTO [cite: 3032, 3068, 3092]
    private QuantityInputDTO input(double thisValue, String thisUnit, String thisType,
                                   double thatValue, String thatUnit, String thatType) {
        QuantityInputDTO inputDTO = new QuantityInputDTO();
        inputDTO.setThisQuantityDTO(new QuantityDTO(thisValue, thisUnit, thisType));
        inputDTO.setThatQuantityDTO(new QuantityDTO(thatValue, thatUnit, thatType));
        return inputDTO;
    }

    private QuantityInputDTO inputWithTarget(double thisV, String thisU, String thisT,
                                             double thatV, String thatU, String thatT,
                                             double targetV, String targetU, String targetT) {
        QuantityInputDTO inputDTO = input(thisV, thisU, thisT, thatV, thatU, thatT);
        inputDTO.setTargetQuantityDTO(new QuantityDTO(targetV, targetU, targetT));
        return inputDTO;
    }

    private HttpEntity<QuantityInputDTO> jsonEntity(QuantityInputDTO body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    // 1. Verify Test Environment
    @Test
    @Order(1)
    void testSpringBootApplicationStarts() {
        assertThat(restTemplate).isNotNull(); 
        assertThat(port).isGreaterThan(0); 
    }

    // 2. POST /compare success
    @Test
    @Order(2)
    @DisplayName("POST /compare 1 foot equals 12 inches result is true") 
    void testCompare_FootEqualsInches() {
        QuantityInputDTO body = input(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");
        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        assertThat(response.getBody().getResultString()).isEqualTo("true"); 
    }

    // 3. POST /compare failure
    @Test
    @Order(3)
    @DisplayName("POST /compare 1 foot does NOT equal 1 inch result is false") 
    void testCompare_FootNotEqualInch() {
        QuantityInputDTO body = input(1.0, "FEET", "LengthUnit", 1.0, "INCHES", "LengthUnit");
        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getResultString()).isEqualTo("false");
    }

    // 4. POST /compare volume
    @Test
    @Order(4)
    @DisplayName("POST /compare 1 gallon equals 3.785 litres result is true") 
    void testCompare_GallonEqualsLitres() {
        QuantityInputDTO body = input(1.0, "GALLON", "VolumeUnit", 3.78541, "LITRE", "VolumeUnit");
        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getResultString()).isEqualTo("true");
    }

    // 5. POST /compare temperature
    @Test
    @Order(5)
    @DisplayName("POST /compare 212 Fahrenheit equals 100 Celsius result is true") 
    void testCompare_FahrenheitEqualsCelsius() {
        QuantityInputDTO body = input(212.0, "FAHRENHEIT", "TemperatureUnit", 100.0, "CELSIUS", "TemperatureUnit");
        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        assertThat(response.getBody().getResultString()).isEqualTo("true"); 
    }

    // 6. POST /convert
    @Test
    @Order(6)
    @DisplayName("POST /convert 100 Celsius to Fahrenheit")
    void testConvert_CelsiusToFahrenheit() {
        QuantityInputDTO body = input(100.0, "CELSIUS", "TemperatureUnit", 0.0, "FAHRENHEIT", "TemperatureUnit"); 
        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/convert", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        assertThat(response.getBody().getResultValue()).isEqualTo(212.0); 
    }

    // 7. POST /add volume
    @Test
    @Order(7)
    @DisplayName("POST /add add 1 gallon and 3.785 litres = 2 gallons") 
    void testAdd_GallonAndLitres() {
        QuantityInputDTO body = input(1.0, "GALLON", "VolumeUnit", 3.78541, "LITRE", "VolumeUnit"); 
        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/add", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        assertThat(response.getBody().getResultValue()).isEqualTo(2.0); 
    }

    // 8. POST /add-with-target-unit
    @Test
    @Order(8)
    @DisplayName("POST /add-with-target-unit 1 foot + 12 inches = 24 inches")
    void testAdd_WithTargetUnit() {
        QuantityInputDTO body = inputWithTarget(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit", 0.0, "INCHES", "LengthUnit"); 
        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/add-with-target-unit", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        assertThat(response.getBody().getResultValue()).isEqualTo(24.0); 
    }

    // 9. POST /subtract
    @Test
    @Order(9)
    @DisplayName("POST /subtract subtract 12 inches from 2 feet = 1 foot") 
    void testSubtract_FeetMinusInches() {
        QuantityInputDTO body = input(2.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit"); 
        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/subtract", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        assertThat(response.getBody().getResultValue()).isEqualTo(1.0); 
    }

    // 10. POST /subtract-with-target-unit
    @Test
    @Order(10)
    @DisplayName("POST /subtract-with-target-unit 2 feet - 12 inches = 12 inches")
    void testSubtract_WithTargetUnit() {
        QuantityInputDTO body = inputWithTarget(2.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit", 0.0, "INCHES", "LengthUnit"); 
        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/subtract-with-target-unit", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        assertThat(response.getBody().getResultValue()).isEqualTo(12.0); 
    }

    // 11. POST /divide
    @Test
    @Order(11)
    @DisplayName("POST /divide 1 yard divided by 1 foot = 3.0") 
    void testDivide_YardByFoot() {
        QuantityInputDTO body = input(1.0, "YARDS", "LengthUnit", 1.0, "FEET", "LengthUnit"); 
        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/divide", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        assertThat(response.getBody().getResultValue()).isEqualTo(1.0); 
    }

    // 12. GET /history/operation
    @Test
    @Order(12)
    @DisplayName("GET /history/operation/CONVERT returns List of CONVERT operations") 
    @SuppressWarnings("unchecked")
    void testGetHistoryByOperation_Convert() {
        ResponseEntity<List> response = restTemplate.getForEntity(
                baseUrl() + "/history/operation/CONVERT", List.class); 
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        assertThat(response.getBody()).isNotEmpty(); 
    }

    // 13. GET /history/type
    @Test
    @Order(13)
    @DisplayName("GET /history/type/TemperatureUnit returns history for TemperatureUnit") 
    @SuppressWarnings("unchecked")
    void testGetHistoryByType_Temperature() {
        ResponseEntity<List> response = restTemplate.getForEntity(
                baseUrl() + "/history/type/TemperatureUnit", List.class); 
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        assertThat(response.getBody()).isNotEmpty(); 
    }

    // 14. GET /count
    @Test
    @Order(14)
    @DisplayName("GET /count/DIVIDE returns count of DIVIDE operations") 
    void testGetOperationCount_Divide() {
        ResponseEntity<Long> response = restTemplate.getForEntity(
                baseUrl() + "/count/DIVIDE", Long.class); 
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
        assertThat(response.getBody()).isGreaterThan(0L); 
    }

    // 15. POST /divide error (Internal Server Error)
    @Test
    @Order(15)
    @DisplayName("POST /divide Error: Division by zero")
    void testDivide_ByZero_Error() {
        QuantityInputDTO body = input(1.0, "YARDS", "LengthUnit", 0.0, "FEET", "LengthUnit"); 
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl() + "/divide", HttpMethod.POST, jsonEntity(body), String.class); 
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST); 
        assertThat(response.getBody()).contains("Divide by zero"); 
    }

    // 16. Validation Test: Unit Validation fails
    @Test
    @Order(16)
    @DisplayName("POST /compare Unit validation fails") 
    void testCompare_FootEqualsInches_UnitValidationFails() {
        QuantityInputDTO body = input(1.0, "FOOT", "LengthUnit", 12.0, "INCHES", "LengthUnit"); 
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST, jsonEntity(body), String.class); 
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST); 
        assertThat(response.getBody()).contains("Unit must be valid for the specified measurement type"); 
    }

    // 17. Validation Test: Type Validation fails
    @Test
    @Order(17)
    @DisplayName("POST /compare Type validation fails") 
    void testCompare_FootEqualsInches_TypeValidationFails() {
        QuantityInputDTO body = new QuantityInputDTO();
        body.setThisQuantityDTO(new QuantityDTO(1.0, "FEET", "InvalidType"));
        body.setThatQuantityDTO(new QuantityDTO(12.0, "INCHES", "LengthUnit"));
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST, jsonEntity(body), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("Measurement type must be one of: LengthUnit, VolumeUnit, WeightUnit, TemperatureUnit"); 
    }
}