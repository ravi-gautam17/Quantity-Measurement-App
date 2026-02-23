package com.apps.quantitymeasurement;

import java.util.Objects;

public class Length {
	
	private static final double EPSILON = 0.0001;
	private double value;
	private LengthUnit unit;
	
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
	private double convertBaseToTargetUnit(double lengthInInches,LengthUnit targetUnit) {
		 return (lengthInInches*unit.getConversionFactor())/targetUnit.getConversionFactor();
	 }
	public Length add(Length l) throws IllegalArgumentException {
		Length len1 = l.convertTo(this.unit);
		
		Length ans = new Length(this.value+len1.getValue(), this.unit);
		
		return ans;
	}
	
	public Length addAndConvert(Length l, LengthUnit unit) throws IllegalArgumentException {
		Length len = l.convertTo(this.unit);
		Length ans = new Length(this.value+len.getValue(), this.unit);
		return ans.convertTo(unit);
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
	
	public LengthUnit getUnit() {
		return unit;
	}

	public Length convertTo(LengthUnit unit) throws IllegalArgumentException{
		double convert = (this.value*this.unit.getConversionFactor())/ unit.getConversionFactor();
		return new Length(convert, unit);
	}
	public static void main(String[] args) {
		Length len1 = new Length(1,LengthUnit.INCHES);
		Length len2 = new Length(12,LengthUnit.INCHES);
		
		System.out.println("Are Length equals? :"+len1.equals(len2));
		
		Length len3 = new Length(1, LengthUnit.YARDS);
		Length len4 = new Length(36, LengthUnit.INCHES);
		
		System.out.println("Are Length equals? :"+len3.equals(len4));
		
		Length len5 = new Length(100, LengthUnit.CENTIMETERS);
		Length len6 = new Length(39.3701, LengthUnit.INCHES);
		
		System.out.println("Are Length equals? :"+len5.equals(len6));
	}
}