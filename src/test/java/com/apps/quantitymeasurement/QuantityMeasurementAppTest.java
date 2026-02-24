package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp.*;
import com.apps.quantitymeasurement.Quantity;
import com.apps.quantitymeasurement.LengthUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {
    	Quantity len1 = new Quantity(2,LengthUnit.FEET);
    	Quantity len2 = new Quantity(2,LengthUnit.FEET);
    	Assertions.assertTrue(len1.equals(len2));
    }
    
    @Test
    public void testIncheEquality() {
    	Quantity len1 = new Quantity(2,LengthUnit.INCHES);
    	Quantity len2 = new Quantity(2,LengthUnit.INCHES);
    	Assertions.assertTrue(len1.equals(len2));
    }
    
    @Test
    public void testFeetIncheComparision() {
    	Quantity len1 = new Quantity(2,LengthUnit.FEET);
    	Quantity len2 = new Quantity(24,LengthUnit.INCHES);
    	Assertions.assertTrue(len1.equals(len2));
    }
    
    @Test
    public void testFeetInEquality() {
    	Quantity len1 = new Quantity(2,LengthUnit.FEET);
    	Quantity len2 = new Quantity(24,LengthUnit.FEET);
    	Assertions.assertTrue(!len1.equals(len2));
    }
    
    @Test
    public void testIncheInEquality() {
    	Quantity len1 = new Quantity(2,LengthUnit.INCHES);
    	Quantity len2 = new Quantity(24,LengthUnit.INCHES);
    	Assertions.assertTrue(!len1.equals(len2));
    }
   
    @ParameterizedTest
    @ValueSource(doubles= {1,4,5,6,5})
    public void multipleFeetcomparison(double feet) {
    	Quantity len1 = new Quantity(feet,LengthUnit.FEET);
    	Quantity len2 = new Quantity(feet*12,LengthUnit.INCHES);
    	Assertions.assertTrue(len1.equals(len2));
    }
    
    @Test 
    public void testEquality_YardToYard_SameValue() {
    	Quantity l1 = new Quantity(1, LengthUnit.YARDS);
    	Quantity l2 = new Quantity(2, LengthUnit.YARDS);
    	Assertions.assertFalse(l1.equals(l2));
    }
    
    @Test 
    public void testEquality_YardToYard_DifferentValue() {
    	Quantity l1 = new Quantity(1, LengthUnit.YARDS);
    	Quantity l2 = new Quantity(2, LengthUnit.YARDS);
    	Assertions.assertFalse(l1.equals(l2));
    }
    
    @Test 
    public void testEquality_YardToFeet_EquivalentValue() {
    	Quantity l1 = new Quantity(1,LengthUnit.YARDS);
    	Quantity l2 = new Quantity(3, LengthUnit.FEET);
    	Assertions.assertTrue(l1.equals(l2));
    }
    
    @Test
    public void testEquality_FeetToYard_EquivalentValue() {
    	Quantity l1 = new Quantity(1,LengthUnit.YARDS);
    	Quantity l2 = new Quantity(3, LengthUnit.FEET);
    	Assertions.assertTrue(l2.equals(l1));
    }
    
    @Test 
    public void testEquality_YardToInches_EquivalentValue() {
    	Quantity l1 = new Quantity(1, LengthUnit.YARDS);
    	Quantity l2 = new Quantity(36, LengthUnit.INCHES);
    	Assertions.assertTrue(l1.equals(l2));
    }
    
    @Test
    public void testEquality_InchesToYard_EquivalentValue() {
    	Quantity l1 = new Quantity(36, LengthUnit.INCHES);
    	Quantity l2 = new Quantity(1, LengthUnit.YARDS);
    	Assertions.assertTrue(l1.equals(l2));
    }
    
    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {
    	Quantity l1 = new Quantity(1, LengthUnit.YARDS);
    	Quantity l2 = new Quantity(2, LengthUnit.FEET);
    	Assertions.assertFalse(l1.equals(l2));
    }
    
    @Test 
    public void testEquality_centimetersToInches_EquivalentValue() {
    	Quantity l1 = new Quantity(1, LengthUnit.CENTIMETERS);
    	Quantity l2 = new Quantity(0.393701, LengthUnit.INCHES);
    	Assertions.assertTrue(l1.equals(l2));
    }
    
    @Test
    public void testEquality_centimetersToFeet_NonEquivalentValue() {
    	Quantity l1 = new Quantity(1, LengthUnit.CENTIMETERS);
    	Quantity l2 = new Quantity(1, LengthUnit.FEET);
    	Assertions.assertFalse(l1.equals(l2));
    }
    
    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
    	Quantity l1 = new Quantity(1, LengthUnit.YARDS);
    	Quantity l2 = new Quantity(3, LengthUnit.FEET);
    	Quantity l3 = new Quantity(36, LengthUnit.INCHES);
    	Assertions.assertTrue(l1.equals(l2));
    	Assertions.assertTrue(l2.equals(l3));
    	Assertions.assertTrue(l1.equals(l3));
    }
    
    @Test
    public void testEquality_YardWithNullUnit() {
    	Quantity l1 = new Quantity(1, LengthUnit.YARDS);
    	Assertions.assertFalse(l1.equals(null));
    }
    
    @Test
    public void testEquality_YardSameReference() {
    	Quantity l1 = new Quantity(1, LengthUnit.YARDS);
    	Assertions.assertTrue(l1.equals(l1));
    }
    
    @Test
    public void testEquality_YardNullComparison() {
    	Quantity l1 = new Quantity(1, LengthUnit.YARDS);
    	Assertions.assertFalse(l1.equals(null));
    }
    
    @Test
    public void testEquality_CentimetersWithNullUnit() {
    	Quantity l1 = new Quantity(1, LengthUnit.CENTIMETERS);
    	Assertions.assertFalse(l1.equals(null));
    }
    
    @Test
    public void testEquality_CentimetersSameReference() {
    	Quantity l1 = new Quantity(1, LengthUnit.CENTIMETERS);
    	Assertions.assertTrue(l1.equals(l1));
    }
    
    @Test
    public void testEquality_CentimetersNullComparison() {
    	Quantity l1 = new Quantity(1, LengthUnit.CENTIMETERS);
    	Assertions.assertFalse(l1.equals(null));
    }
    
    @Test
    public void testEquality_AllUnit_ComplexScenario() {
    	Quantity len1 = new Quantity(2,LengthUnit.YARDS);
    	Quantity len2 = new Quantity(6,LengthUnit.FEET);
    	Quantity len3 = new Quantity(72,LengthUnit.INCHES);
    	Assertions.assertTrue(len1.equals(len2) && len1.equals(len3));
    }
    
    @Test 
    public void testConversion_FeetToInches() {
    	Quantity l1 = new Quantity(1, LengthUnit.FEET);
    	Assertions.assertEquals(12.0, l1.convertTo(LengthUnit.INCHES).getValue());
    }
    
    @Test 
    public void testConversion_InchesToFeet() {
    	Quantity l = new Quantity(24, LengthUnit.INCHES);
    	Assertions.assertEquals(2.0, l.convertTo(LengthUnit.FEET).getValue());
    }
    
    @Test
    public void testConversion_YardsToInches() {
    	Quantity l = new Quantity(1, LengthUnit.YARDS);
    	Assertions.assertEquals(36.0, l.convertTo(LengthUnit.INCHES).getValue());
    }
    
    @Test
    public void testConversion_InchesToYards() {
    	Quantity l = new Quantity(72, LengthUnit.INCHES);
    	Assertions.assertEquals(2.0, l.convertTo(LengthUnit.YARDS).getValue());
    }
    
    @Test 
    public void testConversion_CentimetersToInches() {
    	Quantity l = new Quantity(2.54, LengthUnit.CENTIMETERS);
    	Assertions.assertEquals(1.0, l.convertTo(LengthUnit.INCHES).getValue(), 0.0001);
    }
    
    @Test
    public void testConversion_FeetToYard() {
    	Quantity l = new Quantity(6,LengthUnit.FEET);
    	Assertions.assertEquals(2.0, l.convertTo(LengthUnit.YARDS).getValue());
    }
    
    @Test
    public void testConversion_RoundTrip_PreservesValue() {
    	 Assertions.assertEquals(12,QuantityMeasurementApp
    			 .demonstrateConversion(QuantityMeasurementApp.demonstrateConversion(new Quantity(12.0,LengthUnit.INCHES), LengthUnit.FEET), LengthUnit.INCHES).getValue());            
    }
    
    @Test 
    public void testConversion_ZeroValue() {
    	Quantity l1 = new Quantity(0, LengthUnit.FEET);
    	Assertions.assertEquals(0, l1.convertTo(LengthUnit.YARDS).getValue());
    }
    
    @Test
    public void testConversion_NegativeValue() {
    	Assertions.assertEquals(-12, QuantityMeasurementApp
    			.demonstrateConversion(new Quantity(-1.0, LengthUnit.FEET), LengthUnit.INCHES).getValue());
    }
    
    
    @Test
    public void testConversion_NaNOrInfinite_Throws() {
    	Assertions.assertThrowsExactly(IllegalArgumentException.class, ()->{
    		QuantityMeasurementApp.demonstrateConversion(new Quantity(Double.NaN,LengthUnit.CENTIMETERS),LengthUnit.INCHES);
    	});
    }
    
    @Test 
    public void testConversion_PrecisionTolerance() {
    	Assertions.assertEquals(1.0,QuantityMeasurementApp
    			.demonstrateConversion(new Quantity(2.54, LengthUnit.CENTIMETERS),LengthUnit.INCHES)
    			.getValue(), 1e-6);
    }
    
    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
    	Quantity l1 = new Quantity(1.0, LengthUnit.FEET);
    	Quantity l2 = new Quantity(2.0, LengthUnit.FEET);
    	
    	Assertions.assertEquals(3.0, QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue());
    }
    
    @Test
    public void testAddition_SameUnit_InchPlusInch() {
    	Quantity l1 = new Quantity(6.0, LengthUnit.INCHES);
    	Quantity l2 = new Quantity(6.0, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(12.0, QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue());
    }
    
    @Test 
    public void testAddition_CrossUnit_FeetPlusInches() {
    	Quantity l1 = new Quantity(1.0, LengthUnit.FEET);
    	Quantity l2 = new Quantity(12.0, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(2.0, QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue());
    }
    
    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
    	Quantity l1 = new Quantity(12.0, LengthUnit.INCHES);
    	Quantity l2 = new Quantity(1.0, LengthUnit.FEET);
    	
    	Assertions.assertEquals(24.0, QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue());
    }
    
    @Test 
    public void testAddition_CrossUnit_YardPlusFeet() {
    	Quantity l1 = new Quantity(1.0, LengthUnit.YARDS);
    	Quantity l2 = new Quantity(3.0, LengthUnit.FEET);
    	
    	Assertions.assertEquals(2, QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue());
    }
    
    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
    	Quantity l1 = new Quantity(2.54, LengthUnit.CENTIMETERS);
    	Quantity l2 = new Quantity(1.0, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(5.08, QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue(),0.001);
    }
    
    @Test
    public void testAddition_Commutativity() {
    	Quantity l1 = new Quantity(1.0, LengthUnit.FEET);
    	Quantity l2 = new Quantity(12.0, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue(), QuantityMeasurementApp.demonstrateAddition(l2, l1).convertTo(LengthUnit.FEET).getValue());
    }
    
    @Test
    public void testAddition_WithZero() {
    	Quantity l1 = new Quantity(5, LengthUnit.FEET);
    	Quantity l2 = new Quantity(0, LengthUnit.FEET);
    	
    	Assertions.assertEquals(5.0, QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue());
    }
    
    @Test
    public void testAddition_NegativeValues() {
    	Quantity l1 = new Quantity(5, LengthUnit.FEET);
    	Quantity l2 = new Quantity(-2, LengthUnit.FEET);
    	
    	Assertions.assertEquals(3.0, QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue());
    	
    }
    
    @Test
    public void testAddition_NullSecondOperand() {
    	Quantity l1 = new Quantity(1, LengthUnit.FEET);
    	Assertions.assertThrows(IllegalArgumentException.class,()-> { QuantityMeasurementApp.demonstrateAddition(l1, null);});
    }
    
    @Test
    public void testAddition_LargeValues() {
    	Quantity l1 = new Quantity(1e6, LengthUnit.FEET);
    	Quantity l2 = new Quantity(1e6, LengthUnit.FEET);
    	
    	Assertions.assertEquals(2e6, QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue());
    }
    
    @Test
    public void testAddition_SmallValues() {
    	Quantity l1 = new Quantity(0.001, LengthUnit.FEET);
    	Quantity l2 = new Quantity(0.002, LengthUnit.FEET);
    	
    	Assertions.assertEquals(0.003, QuantityMeasurementApp.demonstrateAddition(l1, l2).getValue(), 0.001);
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
    	Quantity l1 = new Quantity(1, LengthUnit.FEET);
    	Quantity l2 = new Quantity(12, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(2.0, QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.FEET).getValue());
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
    	Quantity l1 = new Quantity(1, LengthUnit.FEET);
    	Quantity l2 = new Quantity(12, LengthUnit.INCHES);
    	Assertions.assertEquals(24.0, QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.INCHES).getValue());
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
    	Quantity l1 = new Quantity(1, LengthUnit.FEET);
    	Quantity l2 = new Quantity(12, LengthUnit.INCHES);
    	Assertions.assertEquals(0.667, QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.YARDS).getValue(),0.001);
    }
    
    @Test 
    public void testAddition_ExplicitTargetUnit_Centimeters() {
    	Quantity l1 = new Quantity(1, LengthUnit.INCHES);
    	Quantity l2 = new Quantity(1, LengthUnit.INCHES);
    	Assertions.assertEquals(5.08, QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.CENTIMETERS).getValue(),0.001);
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
    	Quantity l1 = new Quantity(1, LengthUnit.YARDS);
    	Quantity l2 = new Quantity(3, LengthUnit.FEET);
    	Assertions.assertEquals(2.0, QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.YARDS).getValue());
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
    	Quantity l1 = new Quantity(2, LengthUnit.YARDS);
    	Quantity l2 = new Quantity(3, LengthUnit.FEET);
    	Assertions.assertEquals(9.0, QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.FEET).getValue()); 
    }
    
    @Test 
    public void testAddition_ExplicitTargetUnit_Commutativity() {
    	Quantity l1 = new Quantity(1, LengthUnit.FEET);
    	Quantity l2 = new Quantity(12, LengthUnit.INCHES);
    	Assertions.assertEquals(QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.YARDS).getValue(), QuantityMeasurementApp.demonstrateAddition(l2, l1, LengthUnit.YARDS).getValue()); 
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {
    	Quantity l1 = new Quantity(5, LengthUnit.FEET);
    	Quantity l2 = new Quantity(0, LengthUnit.INCHES);
    	Assertions.assertEquals(1.667, QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.YARDS).getValue(),0.001); 
    }
    
    @Test 
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
    	Quantity l1 = new Quantity(5, LengthUnit.FEET);
    	Quantity l2 = new Quantity(-2, LengthUnit.FEET);
    	
    	Assertions.assertEquals(36.0, QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.INCHES).getValue());
    }
    
    @Test 
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
    	Quantity l1 = new Quantity(5, LengthUnit.FEET);
    	Quantity l2 = new Quantity(-2, LengthUnit.FEET);
    	
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {QuantityMeasurementApp.demonstrateAddition(l1, l2, null); });
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
    	Quantity l1 = new Quantity(1000.0, LengthUnit.FEET);
    	Quantity l2 = new Quantity(500.0, LengthUnit.FEET);
    	Assertions.assertEquals(18000.0, QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.INCHES).getValue());
    }
    
    @Test 
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
    	Quantity l1 = new Quantity(12, LengthUnit.INCHES);
    	Quantity l2 = new Quantity(12, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(0.667, QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.YARDS).getValue(), 0.001);
    }
    
    @Test
    public void testLengthUnitEnum_FeetConstant() {
    	Assertions.assertEquals(1.0, LengthUnit.FEET.getConversionFactor());
    }
    
    @Test
    public void testLengthUnitEnum_InchesConstant() {
    	Assertions.assertEquals(0.0833, LengthUnit.INCHES.getConversionFactor(),0.0001);
    }
    
    @Test
    public void testLengthUnitEnum_YardsConstant() {
    	Assertions.assertEquals(3.0, LengthUnit.YARDS.getConversionFactor());
    }
    
    @Test
    public void testLengthUnitEnum_CentimetersConstant() {
    	Assertions.assertEquals(0.0328, LengthUnit.CENTIMETERS.getConversionFactor(), 0.0001);
    }
    
    @Test
    public void testConvertToBaseUnit_FeetToFeet() {
    	Assertions.assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5));
    }
    
    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
    	Assertions.assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12));
    }
    
    @Test
    public void testConvertToBaseUnit_YardsToFeet() {
    	Assertions.assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1));
    }
    
    @Test
    public void testConvertToBaseUnit_CentimetersToFeet() {
    	Assertions.assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), 0.1);
    }
    
    @Test
    public void testConvertFromBaseUnit_FeetToFeet() {
    	Assertions.assertEquals(1.0, LengthUnit.FEET.convertFromBaseUnit(1.0));
    }
    
    @Test
    public void testConvertFromBaseUnit_FeetToInches() {
    	Assertions.assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1));
    }
    
    @Test
    public void testConvertFromBaseUnit_FeetToYards() {
    	Assertions.assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3));
    }
    
    @Test
    public void testConvertFromBaseUnit_FeetToCentimeters() {
    	Assertions.assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1), 0.01);
    }
    
    @Test
    public void testQuantityLengthRefactored_Equality() {
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateEquality(new Quantity(1, LengthUnit.FEET),new Quantity(12, LengthUnit.INCHES)));
    }
    
    @Test
    public void testQuantityLengthRefactored_ConvertTo() {
    	Assertions.assertEquals(12.0,QuantityMeasurementApp.demonstrateConversion(new Quantity(1.0, LengthUnit.FEET),LengthUnit.INCHES).getValue(),0.1);
    }
    
    @Test
    public void testQuantityLengthRefactored_Add() {
    	Assertions.assertEquals(2.0,QuantityMeasurementApp.demonstrateAddition(new Quantity(1, LengthUnit.FEET),new Quantity(12, LengthUnit.INCHES), LengthUnit.FEET).getValue());
    }
    
    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {
    	Assertions.assertEquals(0.667,QuantityMeasurementApp.demonstrateAddition(new Quantity(1, LengthUnit.FEET), new Quantity(12, LengthUnit.INCHES), LengthUnit.YARDS).getValue(),0.001);
    }
    
    @Test
    public void testQuantityLengthRefactored_NullUnit() {
    	Assertions.assertThrows(IllegalArgumentException.class, ()-> { new Quantity(1, null); });
    }
    
    @Test 
    public void testQuantityLengthRefactored_InvalidValue() {
    	Assertions.assertThrows(IllegalArgumentException.class, ()->{new Quantity(Double.NaN, LengthUnit.FEET); });
    }
    
    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
    	Quantity w1 = new Quantity(1, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(1, WeightUnit.KILOGRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateEquality(w1, w2));
    }
   
    @Test
    public void testEquality_KilogramToKilogram_DifferentValue() {
    	Quantity w1 = new Quantity(1, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(2, WeightUnit.KILOGRAM);
    	Assertions.assertFalse(QuantityMeasurementApp.demonstrateEquality(w1, w2));
    }
    
    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
    	Quantity w1 = new Quantity(1, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(1000, WeightUnit.GRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateEquality(w1, w2));
    }
    
    @Test
    public void testEquality_GramToKilogram_EquivalentValue() {
    	Quantity w1 = new Quantity(1000, WeightUnit.GRAM);
    	Quantity w2 = new Quantity(1, WeightUnit.KILOGRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateEquality(w1, w2));
    }
    
    @Test
    public void testEquality_NullComparison() {
    	Quantity w1 = new Quantity(1, WeightUnit.KILOGRAM);
    	Assertions.assertFalse(QuantityMeasurementApp.demonstrateEquality(w1, null));
    }
    @Test
    public void testEquality_SameReference() {
    	Quantity w = new Quantity(1, WeightUnit.KILOGRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateEquality(w, w));
    }
    @Test
    public void testEquality_NullUnit() {
    	Assertions.assertThrows(IllegalArgumentException.class, ()->{
    		Quantity w= new Quantity(1, null);
    	});
    }
    @Test
    public void testEquality_TransitiveProperty() {
    	Quantity w1= new Quantity(1, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(1000, WeightUnit.GRAM);
    	
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateEquality(w1, w2));
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateEquality(w2, w1));
    }
    
    @Test
    public void testEquality_ZeroValue() {
    	Quantity w1 = new Quantity(0, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(0, WeightUnit.GRAM);
    	
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateEquality(w1, w2));
    }
    
    @Test
    public void testEquality_NegativeWeight() {
    	Quantity w1 = new Quantity(-1, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(-1000, WeightUnit.GRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateEquality(w1, w2));
    }
    @Test
    public void testEquality_LargeWeightValue() {
    	Quantity w1 = new Quantity(1000000, WeightUnit.GRAM);
    	Quantity w2 = new Quantity(1000, WeightUnit.KILOGRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateEquality(w1, w2));
    }
    
    @Test
    public void testEquality_SmallWeightValue() {
    	Quantity w1 = new Quantity(0.001, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(1, WeightUnit.GRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateEquality(w1, w2));
    }
    
    @Test
    public void testConversion_PoundToKilogram() {
    	Quantity w1 = new Quantity(2.20462, WeightUnit.POUND);
    	Assertions.assertEquals(1.0, QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.KILOGRAM).getValue(), 0.1);
    }
    @Test 
    public void testConversion_KilogramToPound() {
    	Quantity w = new Quantity(1, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(2.20462, QuantityMeasurementApp.demonstrateConversion(w, WeightUnit.POUND).getValue(), 0.0001);
    }
    
    @Test
    public void testConversion_SameUnit() {
    	Quantity w1 = new Quantity(5, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(5.0, QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.KILOGRAM).getValue());
    }
    @Test
    public void testConversion_ZeroValue1() {
    	Quantity w = new Quantity(0, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(0, QuantityMeasurementApp.demonstrateConversion(w, WeightUnit.GRAM).getValue());
    }
    
    @Test
    public void testConversion_NegativeValue1() {
    	Quantity w = new Quantity(-1, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(-1000.0, QuantityMeasurementApp.demonstrateConversion(w, WeightUnit.GRAM).getValue());
    }
    @Test
    public void testConversion_RoundTrip() {
    	Quantity w = new Quantity(1.5, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(1.5, QuantityMeasurementApp.demonstrateConversion(QuantityMeasurementApp.demonstrateConversion(w, WeightUnit.GRAM),WeightUnit.KILOGRAM).getValue());
    }
    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
    	Quantity w1 = new Quantity(1, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(2, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(3.0, QuantityMeasurementApp.demonstrateAddition(w1, w2).getValue());
    }
    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
    	Quantity w1 = new Quantity(1, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(1000, WeightUnit.GRAM);
    	Assertions.assertEquals(2.0, QuantityMeasurementApp.demonstrateAddition(w1, w2).getValue());
    }
    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram() {
    	Quantity w1 = new Quantity(2.20462, WeightUnit.POUND);
    	Quantity w2 = new Quantity(1, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(4.40924, QuantityMeasurementApp.demonstrateAddition(w1, w2).getValue(), 0.00001);
    }
    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram() {
    	Quantity w1 = new Quantity(1, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(1000, WeightUnit.GRAM);
    	Assertions.assertEquals(2000.0, QuantityMeasurementApp.demonstrateAddition(w1, w2, WeightUnit.GRAM).getValue());
    }
    @Test
    public void testAddition_Commutativity1() {
    	Quantity w1 = new Quantity(1, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(1000, WeightUnit.GRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateAddition(w1, w2).equals(QuantityMeasurementApp.demonstrateAddition(w2, w1)));
    }
    
    @Test
    public void testAddition_WithZero1() {
    	Quantity w1 = new Quantity(5, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(0, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(5.0, QuantityMeasurementApp.demonstrateAddition(w1, w2).getValue());
    }	
    @Test
    public void testAddition_NegativeValues1() {
    	Quantity w1 = new Quantity(5, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(-2000, WeightUnit.GRAM);
    	Assertions.assertEquals(3.0, QuantityMeasurementApp.demonstrateAddition(w1, w2).getValue());
    }
    @Test
    public void testAddition_LargeValue1s() {
    	Quantity w1 = new Quantity(1e6, WeightUnit.KILOGRAM);
    	Quantity w2 = new Quantity(1e6, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(2e6, QuantityMeasurementApp.demonstrateAddition(w1, w2).getValue());
    }
    @Test
	public void testIMeasurableInterface_LengthUnitImplementation() {
		Quantity<LengthUnit> len1 = new Quantity(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> len2 = new Quantity(1.0, LengthUnit.FEET);
		
		Assertions.assertTrue(len1.equals(len2));
		Assertions.assertEquals(1.0, len2.convertToBaseUnit());
		Assertions.assertEquals(1.0, len1.convertToBaseUnit());
		Assertions.assertEquals(0.084,len1.getUnit().getConversionFactor(),0.001);
		Assertions.assertEquals(12.0,len1.getUnit().convertFromBaseUnit(1.0));
		Assertions.assertEquals(1.0,len1.getUnit().convertToBaseUnit(len1.getValue()));
	}
	
	@Test
	public void testIMeasurableInterface_WeightUnitImplementation() {
		Quantity<WeightUnit> weight1 = new Quantity(1,WeightUnit.KILOGRAM);
		Quantity<WeightUnit> weight2 = new Quantity(1000, WeightUnit.GRAM);
		
		Assertions.assertTrue(weight1.equals(weight2));
		Assertions.assertEquals(1, weight2.convertToBaseUnit());
		Assertions.assertEquals(1000, weight2.getUnit().convertFromBaseUnit(1));
		Assertions.assertEquals(1, weight1.getUnit().convertToBaseUnit(1));
		Assertions.assertEquals(1, weight1.getUnit().getConversionFactor());
	}
}