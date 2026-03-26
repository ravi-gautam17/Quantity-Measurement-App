package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.exception.*;
import com.apps.quantitymeasurement.model.*;
import com.apps.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.apps.quantitymeasurement.unit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    // --- Core Operations ---

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO d1, QuantityDTO d2) {
        try {
            IMeasurable u1 = getValidatedUnit(d1);
            IMeasurable u2 = getValidatedUnit(d2);
            validateCategoryMismatch(u1, u2, "compare");

            // Precision-based comparison (0.0001 delta)
            boolean result = Math.abs(u1.convertToBaseUnit(d1.value) - u2.convertToBaseUnit(d2.value)) < 0.0001;

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                d1.value, d1.unit, d1.measurementType, d2.value, d2.unit, d2.measurementType,
                "COMPARE", null, null, null, String.valueOf(result), false, null);
            return QuantityMeasurementDTO.from(repository.save(entity));
        } catch (Exception e) {
            return saveAndThrowError(d1, d2, "COMPARE", e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityDTO d1, QuantityDTO d2) {
        try {
            IMeasurable u1 = getValidatedUnit(d1);
            IMeasurable u2 = getValidatedUnit(d2);
            validateCategoryMismatch(u1, u2, "convert");

            double convertedValue = u2.convertFromBaseUnit(u1.convertToBaseUnit(d1.value));

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                d1.value, d1.unit, d1.measurementType, d2.value, d2.unit, d2.measurementType,
                "CONVERT", convertedValue, d2.unit, d1.measurementType, null, false, null);
            return QuantityMeasurementDTO.from(repository.save(entity));
        } catch (Exception e) {
            return saveAndThrowError(d1, d2, "CONVERT", e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO add(QuantityDTO d1, QuantityDTO d2) {
        return executeArithmetic(d1, d2, null, "ADD", (a, b) -> a + b);
    }

    @Override
    public QuantityMeasurementDTO add(QuantityDTO d1, QuantityDTO d2, QuantityDTO target) {
        return executeArithmetic(d1, d2, target, "ADD", (a, b) -> a + b);
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO d1, QuantityDTO d2) {
        return executeArithmetic(d1, d2, null, "SUBTRACT", (a, b) -> a - b);
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO d1, QuantityDTO d2, QuantityDTO target) {
        return executeArithmetic(d1, d2, target, "SUBTRACT", (a, b) -> a - b);
    }

    @Override
    public QuantityMeasurementDTO divide(QuantityDTO d1, QuantityDTO d2) {
        if (d2.value == 0) throw new QuantityMeasurementException("Divide by zero");
        return executeArithmetic(d1, d2, null, "DIVIDE", (a, b) -> a / b);
    }

    // --- Private Engine (Reduces Code Bloat) ---

    private QuantityMeasurementDTO executeArithmetic(QuantityDTO d1, QuantityDTO d2, QuantityDTO target, 
                                                   String op, DoubleBinaryOperator math) {
        try {
            IMeasurable u1 = getValidatedUnit(d1);
            IMeasurable u2 = getValidatedUnit(d2);
            validateCategoryMismatch(u1, u2, op.toLowerCase());

            double baseResult = math.applyAsDouble(u1.convertToBaseUnit(d1.value), u2.convertToBaseUnit(d2.value));
            IMeasurable t = (target != null) ? getValidatedUnit(target) : u1;
            double finalValue = t.convertFromBaseUnit(baseResult);

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                d1.value, d1.unit, d1.measurementType, d2.value, d2.unit, d2.measurementType,
                op, finalValue, t.getUnitName(), d1.measurementType, null, false, null);
            
            return QuantityMeasurementDTO.from(repository.save(entity));
        } catch (Exception e) {
            return saveAndThrowError(d1, d2, op, e.getMessage());
        }
    }

    // --- History & Count (Using Stream API) ---

    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
        return repository.findByOperation(operation.toUpperCase()).stream()
                .map(QuantityMeasurementDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuantityMeasurementDTO> getMeasurementsByType(String type) {
        return repository.findByThisMeasurementType(type).stream()
                .map(QuantityMeasurementDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperationAndIsErrorFalse(operation.toUpperCase());
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        return repository.findByIsErrorTrue().stream()
                .map(QuantityMeasurementDTO::from)
                .collect(Collectors.toList());
    }

    // --- Validation Helpers ---

    private IMeasurable getValidatedUnit(QuantityDTO dto) {
        try {
            return switch (dto.measurementType) {
                case "LengthUnit" -> LengthUnit.valueOf(dto.unit);
                case "VolumeUnit" -> VolumeUnit.valueOf(dto.unit);
                case "WeightUnit" -> WeightUnit.valueOf(dto.unit);
                case "TemperatureUnit" -> TemperatureUnit.valueOf(dto.unit);
                default -> throw new Exception();
            };
        } catch (Exception e) {
            throw new InvalidUnitException("Unit must be valid for the specified measurement type");
        }
    }

    private void validateCategoryMismatch(IMeasurable u1, IMeasurable u2, String op) {
        if (!u1.getClass().equals(u2.getClass())) {
            throw new CategoryMismatchException(op + " Error: Cannot perform arithmetic between different measurement categories: " 
                + u1.getClass().getSimpleName() + " and " + u2.getClass().getSimpleName());
        }
    }

    private QuantityMeasurementDTO saveAndThrowError(QuantityDTO d1, QuantityDTO d2, String op, String msg) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
            d1.value, d1.unit, d1.measurementType, d2.value, d2.unit, d2.measurementType,
            op, null, null, null, null, true, msg);
        repository.save(entity);
        throw new QuantityMeasurementException(msg);
    }
}