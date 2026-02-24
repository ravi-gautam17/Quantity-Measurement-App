package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.Quantity;
import com.apps.quantitymeasurement.LengthUnit;
import com.apps.quantitymeasurement.WeightUnit;
import com.apps.quantitymeasurement.IMeasurable;


public class QuantityMeasurementApp {
	
	public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {
		return quantity1.equals(quantity2);
	}
	
	public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit){
		if(quantity==null || targetUnit==null) {
			throw new IllegalArgumentException("quantity and unit cannot be null");
		}
		return quantity.convertTo(targetUnit);
	}
	
	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2){
		if(quantity1==null || quantity2==null)
			throw new IllegalArgumentException("Quantity cannot be null");
		return quantity1.add(quantity2);
	}
	public static<U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
		if(quantity1==null || quantity2==null || targetUnit==null)
			throw new IllegalArgumentException("Quantity and unit cannot be null");
		Quantity<U> ans = quantity1.add(quantity2, targetUnit);
		return ans;
	}
	
	public static void main(String[] args) {
		Quantity<WeightUnit> weightInGrams = new Quantity(1000.0, WeightUnit.GRAM);
		Quantity<WeightUnit> weightInKilograms = new Quantity(1.0, WeightUnit.KILOGRAM);
		boolean areWeightsEqual = demonstrateEquality(weightInGrams, weightInKilograms);
		System.out.println("Are weights equals? "+areWeightsEqual);
		
		Quantity<WeightUnit> convertedWeight = demonstrateConversion(weightInGrams, WeightUnit.KILOGRAM);
		System.out.println("Converted Weight: "+convertedWeight.getValue()+" "+convertedWeight.getUnit());
		
		Quantity<WeightUnit> weightInPounds = new Quantity(2.20462, WeightUnit.POUND);
		Quantity<WeightUnit> sumWeight = demonstrateAddition(weightInKilograms, weightInPounds);
		System.out.println("Sum weight: "+sumWeight.getValue()+" "+sumWeight.getUnit());
		
		Quantity<WeightUnit> sumWeightInGrams = demonstrateAddition(weightInKilograms, weightInPounds, WeightUnit.GRAM);
		System.out.println("Sum weight: "+sumWeightInGrams.getValue()+" "+sumWeightInGrams.getUnit());
	}
}