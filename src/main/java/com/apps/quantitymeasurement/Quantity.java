package com.apps.quantitymeasurement;

public class Quantity<U extends IMeasurable> {
	private static final double EPSILON = 0.0001;
	private final Double value;
	private final U unit;
	
	public Quantity(double value, U unit) {
		
		if(Double.isNaN(value) || Double.isInfinite(value))
			throw new IllegalArgumentException("Value is invalid");
		if(unit==null)
			throw new IllegalArgumentException("Unit cannot be null");
		this.value= value;
		this.unit = unit;
	}
	public double getValue() {
		return value;
	}
	public U getUnit() {
		return unit;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null || this.getClass()!=obj.getClass()) return false;
		
		Quantity quan = (Quantity) obj;
		
		return Math.abs(this.value-quan.convertTo(this.unit).getValue())<EPSILON;
	}
	
	public Quantity<U> convertTo(U targetUnit) {
		double value = this.value*this.unit.getConversionFactor()/targetUnit.getConversionFactor();
		return new Quantity(value, targetUnit);
	}
	public Quantity<U>  add(Quantity<U> quan){
		quan = quan.convertTo(this.unit);
		Quantity add = new Quantity(this.getValue()+quan.getValue(), this.getUnit());
		return add;
	}
	
	public Quantity<U> add(Quantity<U> quan, U unit){
		quan = quan.convertTo(this.unit);
		Quantity add = new Quantity(this.getValue()+quan.getValue(),this.unit);
		
		return add.convertTo(unit);
	}
	public double convertToBaseUnit() {
		return value*unit.getConversionFactor();
	}
	
	@Override 
	public int hashCode(){
		double base =convertToBaseUnit();
	    long rounded = Math.round(base / EPSILON);
	    return Long.hashCode(rounded);
	}
	
	@Override 
	public String toString() {
		return "Qunatity [ value="+value+", unit="+unit+"]";
	}
	public static void main(String[] args) {
		Quantity<LengthUnit> lengthInFeet = new Quantity(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> lengthInInches = new Quantity(120.0, LengthUnit.INCHES);
		boolean isEqual = lengthInFeet.equals(lengthInInches);
		System.out.println("Are lengths equal?: "+isEqual);
		
		Quantity<WeightUnit> weightInKilogram = new Quantity(1.0,  WeightUnit.KILOGRAM);
		Quantity<WeightUnit> weightInGram = new Quantity(1000.0, WeightUnit.GRAM);
		isEqual = weightInKilogram.equals(weightInGram);
		System.out.println("Are weights equal?: "+isEqual);
		
		double convertedLength = lengthInFeet.convertTo(LengthUnit.INCHES).getValue();
		System.out.println("!0 feet in inches: "+convertedLength);
		
		Quantity<LengthUnit> totalLength = lengthInFeet.add(lengthInInches);
		System.out.println(totalLength);
		
		Quantity<WeightUnit> weightInPounds = new Quantity(2.0, WeightUnit.POUND);
		Quantity<WeightUnit> totalWeight = weightInKilogram.add(weightInGram);
		System.out.println(totalWeight);
  	}
}