package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.IMeasurable;

public enum LengthUnit implements IMeasurable{
	FEET(1.0),
	INCHES(1.0/12.0),
	YARDS(3.0),
	CENTIMETERS(0.0328084);
	
	private final double conversionFactor;
	
	LengthUnit(double conversionFactor){
		this.conversionFactor=conversionFactor;
	}
	
	public double getConversionFactor() {
		return conversionFactor;
	}
	public double convertToBaseUnit(double value) {
		return value*this.getConversionFactor();
	}
	public double convertFromBaseUnit(double value) {
		return value*LengthUnit.FEET.getConversionFactor()/this.getConversionFactor();
	}
	public String getUnitName() {
		return LengthUnit.this.name();
	}
}