package com.apps.quantitymeasurement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuantityMeasurementDTO {
    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;
    private double thatValue;
    private String thatUnit;
    private String thatMeasurementType;
    private String operation;
    private String resultString;
    private double resultValue;
    private String resultUnit;
    private String resultMeasurementType;
    private String errorMessage;

    @JsonProperty("error")
    private boolean error;

    public static QuantityMeasurementDTO from(QuantityMeasurementEntity entity) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(entity.getThisValue());
        dto.setThisUnit(entity.getThisUnit());
        dto.setThisMeasurementType(entity.getThisMeasurementType());
        dto.setThatValue(entity.getThatValue());
        dto.setThatUnit(entity.getThatUnit());
        dto.setThatMeasurementType(entity.getThatMeasurementType());
        dto.setOperation(entity.getOperation());
        dto.setResultString(entity.getResultString());
        dto.setResultValue(entity.getResultValue() != null ? entity.getResultValue() : 0.0);
        dto.setResultUnit(entity.getResultUnit());
        dto.setResultMeasurementType(entity.getResultMeasurementType());
        dto.setErrorMessage(entity.getErrorMessage());
        dto.setError(entity.isError());
        return dto;
    }

    public static List<QuantityMeasurementDTO> fromList(List<QuantityMeasurementEntity> list) {
        return list.stream().map(QuantityMeasurementDTO::from).collect(Collectors.toList());
    }
}