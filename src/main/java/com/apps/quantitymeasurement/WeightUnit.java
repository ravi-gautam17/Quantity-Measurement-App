package com.apps.quantitymeasurement;

public enum WeightUnit {
	KILOGRAM(1.0),
	GRAM(0.001),
	POUND(0.453592);
	
	public final double conversionFactor;
	
	WeightUnit(double conversionFactor) {
		this.conversionFactor= conversionFactor;
	}
	
	public double getConversionFactor() {
		return this.conversionFactor;
	}
	public double convertToBaseUnit(double value) {
		return this.conversionFactor*value;
	}
	public double convertFromBaseUnit(double value) {
		return value*WeightUnit.KILOGRAM.getConversionFactor()/this.getConversionFactor();
	}
}