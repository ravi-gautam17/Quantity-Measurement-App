package com.apps.quantitymeasurement.model;

import java.time.LocalDateTime;

import jakarta.persistence .*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quantity_measurement_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;
    private double thatValue;
    private String thatUnit;
    private String thatMeasurementType;

    private String operation; 
    
    // Using Double wrapper allows true NULL values in DB
    private Double resultValue; 
    private String resultUnit;
    private String resultMeasurementType;
    private String resultString;
    
    private boolean isError;
    private String errorMessage;
    
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() { this.createdAt = LocalDateTime.now(); }

    // Unified constructor for Service/DTO usage
    public QuantityMeasurementEntity(double thisVal, String thisU, String thisType, 
                                     double thatVal, String thatU, String thatType, 
                                     String op, Double resVal, String resU, String resType, 
                                     String resStr, boolean err, String errMsg) {
        this.thisValue = thisVal; this.thisUnit = thisU; this.thisMeasurementType = thisType;
        this.thatValue = thatVal; this.thatUnit = thatU; this.thatMeasurementType = thatType;
        this.operation = op; this.resultValue = resVal; this.resultUnit = resU;
        this.resultMeasurementType = resType; this.resultString = resStr;
        this.isError = err; this.errorMessage = errMsg;
    }
}