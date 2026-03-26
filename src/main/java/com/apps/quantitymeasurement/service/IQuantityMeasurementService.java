package com.apps.quantitymeasurement.service;

import java.util.*;

import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.model.QuantityMeasurementDTO;

public interface IQuantityMeasurementService {
	
	public QuantityMeasurementDTO compare(QuantityDTO thisQuantityDTO, 
			QuantityDTO thatQuantityDTO);
	
	public QuantityMeasurementDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);
	
	public QuantityMeasurementDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);
	
	public QuantityMeasurementDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO);
	
	public QuantityMeasurementDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);
	
	public QuantityMeasurementDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnit);
	
	public QuantityMeasurementDTO divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantity);
	
	List<QuantityMeasurementDTO> getOperationHistory(String operation);
	
	List<QuantityMeasurementDTO> getMeasurementsByType(String type);
	
	long getOperationCount(String operation);
	
	List<QuantityMeasurementDTO> getErrorHistory();
}
