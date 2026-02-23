package com.apps.quantitymeasurement;

public class Weight {
	private static final double EPSILON = 0.0001;
	private double value;
	private WeightUnit unit;
	
	public Weight(double value, WeightUnit unit) {
		
		if(Double.isNaN(value) || Double.isInfinite(value)) {
			throw new IllegalArgumentException("Value cannot be null");
		}
		if(unit==null) throw new IllegalArgumentException("Unit cannot be null");
		this.value= value;
		this.unit =unit;
	}
	public double getValue() {
		return value;
	}
	public WeightUnit getUnit() {
		return unit;
	}
	private double convertToBaseUnit() {
		return value*unit.getConversionFactor();
	}
	public boolean compare(Weight weightunit) {
		if(weightunit==null) return false;
		
		return Math.abs(this.convertToBaseUnit()-weightunit.convertToBaseUnit())<this.EPSILON;
	}
	
	public double convertBaseToTargetUnit(double value, WeightUnit unit) {
		return value*this.unit.getConversionFactor()/unit.getConversionFactor();
	}
	public Weight add(Weight w) throws IllegalArgumentException{
		if(w==null) throw new IllegalArgumentException("Weight cannot be null");
		w= w.convertTo(this.unit);
		Weight add = new Weight(this.value+w.getValue(), this.unit);
		return add;
	}
	public Weight convertTo(WeightUnit unit) throws IllegalArgumentException{
		double convert = (this.value*this.unit.getConversionFactor())/unit.getConversionFactor();
		return new Weight(convert, unit);
	}
	public Weight add(Weight w, WeightUnit unit) {
		if(w==null || unit==null) throw new IllegalArgumentException("Weight and unit cannot ne null");
		w= w.convertTo(this.unit);
		
		Weight ans =this.add(w);
		return ans.convertTo(unit);
	}
	
	@Override 
	public boolean equals(Object obj) throws IllegalArgumentException{
		if(this==obj) return true;
		
		if(obj==null || this.getClass()!=obj.getClass()) return false;
		Weight w = (Weight) obj;
		return this.compare(w);
	}
	
	@Override 
	public int hashCode() {
		double base = convertToBaseUnit();
	    long rounded = Math.round(base / EPSILON);
	    return Long.hashCode(rounded);
	}
	@Override
	public String toString() {
		return "Weight [ value="+value+", unit="+unit+"]";
	}
	public static void main(String args[]) {
		Weight w1 = new Weight(1,WeightUnit.KILOGRAM);
		Weight w2 = new Weight(1000, WeightUnit.GRAM);
		
		System.out.println("Are Weight equals? :"+w1.equals(w2));
		
		Weight w3 = new Weight(1, WeightUnit.KILOGRAM);
		Weight w4 = new Weight(2.20462, WeightUnit.POUND);
		
		System.out.println("Are Weight equals? :"+w3.equals(w4));
		
	}
}