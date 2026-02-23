package com.apps.quantitymeasurement;

public class Length {
	private double value;
	private LengthUnit unit;
	
	public enum LengthUnit{
		FEET(12.0),
		INCHES(1.0);
		
		private final double conversionFactor;
		
		LengthUnit(double conversionFactor){
			this.conversionFactor=conversionFactor;
		}
		
		public double getConversionFactor() {
			return conversionFactor;
		}
	}
	
	public Length(double value, LengthUnit unit) {
		if(Double.isNaN(value)) {
			throw new IllegalArgumentException("Invalid Exception");
		}
		this.value= value;
		this.unit=unit;
	}
	
	private double convertToBaseUnit() {
		return value*unit.getConversionFactor();
	}
	public boolean compare(Length lengthUnit) {
		if(convertToBaseUnit()==lengthUnit.convertToBaseUnit()) {
			return true;
		}
		return false;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(this==obj) return true;
		
		if(obj==null || this.getClass()!=obj.getClass()) return false;
		Length l = (Length)obj;
		return this.compare(l);
	}
	
	public static void main(String[] args) {
		Length len1 = new Length(1,Length.LengthUnit.INCHES);
		Length len2 = new Length(12,Length.LengthUnit.INCHES);
		
		System.out.println("Are Length equals? :"+len1.equals(len2));
	}
}