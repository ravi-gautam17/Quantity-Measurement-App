package com.apps.quantitymeasurement;

import java.util.Objects;

public class Length {
	
	private static final double EPSILON = 0.0001;
	private double value;
	private LengthUnit unit;
	
	public enum LengthUnit{
		FEET(12.0),
		INCHES(1.0),
		YARDS(36.0),
		CENTIMETERS(0.393701);
		
		private final double conversionFactor;
		
		LengthUnit(double conversionFactor){
			this.conversionFactor=conversionFactor;
		}
		
		public double getConversionFactor() {
			return conversionFactor;
		}
	}
	
	public Length(double value, LengthUnit unit) {
		if(Double.isNaN(value) || Double.isInfinite(value)) {
			throw new IllegalArgumentException("Invalid numeric value");
		}
		if(unit ==null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		this.value= value;
		this.unit=unit;
	}
	
	private double convertToBaseUnit() {
		return value*unit.getConversionFactor();
	}
	public boolean compare(Length lengthUnit) {
		if(lengthUnit==null) return false;
		
		return Math.abs(this.convertToBaseUnit()-lengthUnit.convertToBaseUnit()) <EPSILON;
	}
	
	public Length add(Length l) throws IllegalArgumentException {
		Length len1 = l.convertTo(this.unit);
		
		Length ans = new Length(this.value+len1.getValue(), this.unit);
		
		return ans;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(this==obj) return true;
		
		if(obj==null || this.getClass()!=obj.getClass()) return false;
		Length l = (Length)obj;
		return this.compare(l);
	}
	 @Override
	    public int hashCode() {
	        return Objects.hash(convertToBaseUnit());
	    }
	public double getValue() {
		return this.value;
	}
	@Override 
	public String toString() {
		return "Length [value="+value+", unit="+unit+"]";
	}
	
	public Length convertTo(LengthUnit unit) throws IllegalArgumentException{
		double convert = (this.value*this.unit.getConversionFactor())/ unit.getConversionFactor();
		return new Length(convert, unit);
	}
	public static void main(String[] args) {
		Length len1 = new Length(1,Length.LengthUnit.INCHES);
		Length len2 = new Length(12,Length.LengthUnit.INCHES);
		
		System.out.println("Are Length equals? :"+len1.equals(len2));
		
		Length len3 = new Length(1, Length.LengthUnit.YARDS);
		Length len4 = new Length(36, Length.LengthUnit.INCHES);
		
		System.out.println("Are Length equals? :"+len3.equals(len4));
		
		Length len5 = new Length(100, LengthUnit.CENTIMETERS);
		Length len6 = new Length(39.3701, LengthUnit.INCHES);
		
		System.out.println("Are Length equals? :"+len5.equals(len6));
	}
}