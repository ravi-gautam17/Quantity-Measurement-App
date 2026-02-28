package com.apps.quantitymeasurement;
import com.apps.quantitymeasurement.IMeasurable;

public enum VolumeUnit implements IMeasurable{
	LITRE(1.0),
	MILLILETRE(0.001),
	GALLON(3.78541);
	
	public final double conversionFactor;
	
	VolumeUnit(double conversionFactor){
		this.conversionFactor= conversionFactor;
	}

	@Override
	public double getConversionFactor() {
		// TODO Auto-generated method stub
		return this.conversionFactor;
	}

	@Override
	public double convertToBaseUnit(double value) {
		// TODO Auto-generated method stub
		return this.conversionFactor*value;
	}

	@Override
	public double convertFromBaseUnit(double baseValue) {
		// TODO Auto-generated method stub
		return this.LITRE.getConversionFactor()*baseValue/this.getConversionFactor();
	}

	@Override
	public String getUnitName() {
		// TODO Auto-generated method stub
		return VolumeUnit.this.name();
	}
	
	
}