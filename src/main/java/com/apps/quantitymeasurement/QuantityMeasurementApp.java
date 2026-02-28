package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.Quantity;
import com.apps.quantitymeasurement.LengthUnit;
import com.apps.quantitymeasurement.WeightUnit;
import com.apps.quantitymeasurement.IMeasurable;


public class QuantityMeasurementApp {
	
	public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) throws IllegalArgumentException {
		if(quantity1==null || quantity2==null) return false;
		if(quantity1.getUnit().getClass()!=quantity2.getUnit().getClass()) return false;
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
		
		Quantity<VolumeUnit> volumeInLitre = new Quantity(1, VolumeUnit.LITRE);
		Quantity<VolumeUnit> volumeInLitre2 = new Quantity(1, VolumeUnit.LITRE);
		boolean isEqual = volumeInLitre.equals(volumeInLitre2);
		System.out.println("Volume are equal: "+ isEqual);
		
		Quantity<VolumeUnit> volumeInMillilitre = new Quantity(1000, VolumeUnit.MILLILETRE);
		boolean isEqualMilli = volumeInLitre.equals(volumeInMillilitre);
		System.out.println("1 Litre equals 1000 millilitres: "+isEqualMilli);
		
		Quantity<VolumeUnit> volumeInGallon = new Quantity(0.264172, VolumeUnit.GALLON);
		boolean isEqualGallon = volumeInLitre.equals(volumeInGallon);
		System.out.println("1 Litre equals 0.264172 Gallon: "+isEqualGallon);
		
		Quantity<VolumeUnit> volumInHalfMilli = new Quantity(500, VolumeUnit.MILLILETRE);
		Quantity<VolumeUnit> volumeInHalfLitre = new Quantity(0.5, VolumeUnit.LITRE);
		boolean isEquals = volumeInHalfLitre.equals(volumeInHalfLitre);
		System.out.println("0.5 Litre equals 500 millilitre: "+isEquals);
		
		Quantity<VolumeUnit> volumeInLitre3 = new Quantity(3.78541, VolumeUnit.LITRE);
		Quantity<VolumeUnit> volumeInGallon2 = new Quantity(1, VolumeUnit.GALLON);
		System.out.println("3.78541 litres equals to 1 Gallon: "+volumeInLitre3.equals(volumeInGallon2));
		
		System.out.println("1 Litre in Millilitre equals to: "+ volumeInLitre.convertTo(VolumeUnit.MILLILETRE).getValue());
		
		System.out.println("2 Gallon in Litre: "+ QuantityMeasurementApp.demonstrateConversion(new Quantity(2,VolumeUnit.GALLON), VolumeUnit.LITRE).getValue());
		
		System.out.println("500 millilitre in Gallon: "+QuantityMeasurementApp.demonstrateConversion(new Quantity(500,VolumeUnit.MILLILETRE), VolumeUnit.GALLON).getValue());
		
		System.out.println("Convert Litre to Litre: "+QuantityMeasurementApp.demonstrateConversion(volumeInLitre, VolumeUnit.LITRE).getValue());
		
		System.out.println("Litre to Litre addition: "+ QuantityMeasurementApp.demonstrateAddition(new Quantity(1,VolumeUnit.LITRE), new Quantity(2,VolumeUnit.LITRE)));
		
		System.out.println("Litre and MilliLitre addition: "+QuantityMeasurementApp.demonstrateAddition(new Quantity(1, VolumeUnit.LITRE), new Quantity(1000, VolumeUnit.MILLILETRE)));
		
		System.out.println("MilliLitre and Litre addition: "+QuantityMeasurementApp.demonstrateAddition(new Quantity(500, VolumeUnit.MILLILETRE), new Quantity(0.5, VolumeUnit.LITRE)));
		
		System.out.println("Gallon and Litre addition: "+QuantityMeasurementApp.demonstrateAddition(new Quantity(2, VolumeUnit.GALLON), new Quantity(3.78541, VolumeUnit.LITRE)));
		
		System.out.println("Litre and MilliLitre addition and convert to MilliLitre: "+QuantityMeasurementApp.demonstrateAddition(new Quantity(1, VolumeUnit.LITRE), new Quantity(1000, VolumeUnit.MILLILETRE), VolumeUnit.MILLILETRE));
		
		System.out.println("Gallon and Litre addition then convert to Gallon: "+QuantityMeasurementApp.demonstrateAddition(new Quantity(1, VolumeUnit.GALLON), new Quantity(3.78541, VolumeUnit.LITRE), VolumeUnit.GALLON));
	}
}