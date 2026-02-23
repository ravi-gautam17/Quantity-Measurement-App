package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp.*;
import com.apps.quantitymeasurement.Length;
import com.apps.quantitymeasurement.LengthUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class QuantityMeasurementAppTest {

    @Test
    public void twoSameFeetValues() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        Assertions.assertEquals(feet1, feet2);
    }

    @Test
    public void twoDifferentFeetValues() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(2.0);
        Assertions.assertNotEquals(feet1, feet2);
    }

    @Test
    public void feetValueAndNull() {
        Feet feet1 = new Feet(1.0);
        Assertions.assertNotEquals(null, feet1);
    }

    @Test
    public void sameFeetReference() {
        Feet feet1 = new Feet(1.0);
        Assertions.assertEquals(feet1, feet1);
    }

    @Test
    public void feetAndDifferentType() {
        Feet feet1 = new Feet(1.0);
        Object obj = new Object();
        Assertions.assertNotEquals(feet1, obj);
    }
    
    @Test
    public void twoSameInchesValues() {
        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(1.0);
        Assertions.assertEquals(inches1, inches2);
    }

    @Test
    public void twoDifferentInchesValues() {
    	Inches inches1 = new Inches(1.0);
    	Inches inches2 = new Inches(2.0);
        Assertions.assertNotEquals(inches1, inches2);
    }

    @Test
    public void inchesValueAndNull() {
    	Inches inches1 = new Inches(1.0);
        Assertions.assertNotEquals(null, inches1);
    }

    @Test
    public void sameInchesReference() {
    	Inches inches1 = new Inches(1.0);
        Assertions.assertEquals(inches1, inches1);
    }

    @Test
    public void inchesAndDifferentType() {
    	Inches inches1 = new Inches(1.0);
        Object obj = new Object();
        Assertions.assertNotEquals(inches1, obj);
    }
    @Test
    public void testFeetEquality() {
    	Length len1 = new Length(2,LengthUnit.FEET);
    	Length len2 = new Length(2,LengthUnit.FEET);
    	Assertions.assertTrue(len1.equals(len2));
    }
    
    @Test
    public void testIncheEquality() {
    	Length len1 = new Length(2,LengthUnit.INCHES);
    	Length len2 = new Length(2,LengthUnit.INCHES);
    	Assertions.assertTrue(len1.equals(len2));
    }
    
    @Test
    public void testFeetIncheComparision() {
    	Length len1 = new Length(2,LengthUnit.FEET);
    	Length len2 = new Length(24,LengthUnit.INCHES);
    	Assertions.assertTrue(len1.compare(len2));
    }
    
    @Test
    public void testFeetInEquality() {
    	Length len1 = new Length(2,LengthUnit.FEET);
    	Length len2 = new Length(24,LengthUnit.FEET);
    	Assertions.assertTrue(!len1.equals(len2));
    }
    
    @Test
    public void testIncheInEquality() {
    	Length len1 = new Length(2,LengthUnit.INCHES);
    	Length len2 = new Length(24,LengthUnit.INCHES);
    	Assertions.assertTrue(!len1.equals(len2));
    }
   
    @ParameterizedTest
    @ValueSource(doubles= {1,4,5,6,5})
    public void multipleFeetcomparison(double feet) {
    	Length len1 = new Length(feet,LengthUnit.FEET);
    	Length len2 = new Length(feet*12,LengthUnit.INCHES);
    	Assertions.assertTrue(len1.compare(len2));
    }
    
    @Test 
    public void testEquality_YardToYard_SameValue() {
    	Length l1 = new Length(1, LengthUnit.YARDS);
    	Length l2 = new Length(2, LengthUnit.YARDS);
    	Assertions.assertFalse(l1.equals(l2));
    }
    
    @Test 
    public void testEquality_YardToYard_DifferentValue() {
    	Length l1 = new Length(1, LengthUnit.YARDS);
    	Length l2 = new Length(2, LengthUnit.YARDS);
    	Assertions.assertFalse(l1.equals(l2));
    }
    
    @Test 
    public void testEquality_YardToFeet_EquivalentValue() {
    	Length l1 = new Length(1,LengthUnit.YARDS);
    	Length l2 = new Length(3, LengthUnit.FEET);
    	Assertions.assertTrue(l1.equals(l2));
    }
    
    @Test
    public void testEquality_FeetToYard_EquivalentValue() {
    	Length l1 = new Length(1,LengthUnit.YARDS);
    	Length l2 = new Length(3, LengthUnit.FEET);
    	Assertions.assertTrue(l2.equals(l1));
    }
    
    @Test 
    public void testEquality_YardToInches_EquivalentValue() {
    	Length l1 = new Length(1, LengthUnit.YARDS);
    	Length l2 = new Length(36, LengthUnit.INCHES);
    	Assertions.assertTrue(l1.equals(l2));
    }
    
    @Test
    public void testEquality_InchesToYard_EquivalentValue() {
    	Length l1 = new Length(36, LengthUnit.INCHES);
    	Length l2 = new Length(1, LengthUnit.YARDS);
    	Assertions.assertTrue(l1.equals(l2));
    }
    
    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {
    	Length l1 = new Length(1, LengthUnit.YARDS);
    	Length l2 = new Length(2, LengthUnit.FEET);
    	Assertions.assertFalse(l1.equals(l2));
    }
    
    @Test 
    public void testEquality_centimetersToInches_EquivalentValue() {
    	Length l1 = new Length(1, LengthUnit.CENTIMETERS);
    	Length l2 = new Length(0.393701, LengthUnit.INCHES);
    	Assertions.assertTrue(l1.equals(l2));
    }
    
    @Test
    public void testEquality_centimetersToFeet_NonEquivalentValue() {
    	Length l1 = new Length(1, LengthUnit.CENTIMETERS);
    	Length l2 = new Length(1, LengthUnit.FEET);
    	Assertions.assertFalse(l1.equals(l2));
    }
    
    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
    	Length l1 = new Length(1, LengthUnit.YARDS);
    	Length l2 = new Length(3, LengthUnit.FEET);
    	Length l3 = new Length(36, LengthUnit.INCHES);
    	Assertions.assertTrue(l1.equals(l2));
    	Assertions.assertTrue(l2.equals(l3));
    	Assertions.assertTrue(l1.equals(l3));
    }
    
    @Test
    public void testEquality_YardWithNullUnit() {
    	Length l1 = new Length(1, LengthUnit.YARDS);
    	Assertions.assertFalse(l1.equals(null));
    }
    
    @Test
    public void testEquality_YardSameReference() {
    	Length l1 = new Length(1, LengthUnit.YARDS);
    	Assertions.assertTrue(l1.equals(l1));
    }
    
    @Test
    public void testEquality_YardNullComparison() {
    	Length l1 = new Length(1, LengthUnit.YARDS);
    	Assertions.assertFalse(l1.equals(null));
    }
    
    @Test
    public void testEquality_CentimetersWithNullUnit() {
    	Length l1 = new Length(1, LengthUnit.CENTIMETERS);
    	Assertions.assertFalse(l1.equals(null));
    }
    
    @Test
    public void testEquality_CentimetersSameReference() {
    	Length l1 = new Length(1, LengthUnit.CENTIMETERS);
    	Assertions.assertTrue(l1.equals(l1));
    }
    
    @Test
    public void testEquality_CentimetersNullComparison() {
    	Length l1 = new Length(1, LengthUnit.CENTIMETERS);
    	Assertions.assertFalse(l1.equals(null));
    }
    
    @Test
    public void testEquality_AllUnit_ComplexScenario() {
    	Length len1 = new Length(2,LengthUnit.YARDS);
    	Length len2 = new Length(6,LengthUnit.FEET);
    	Length len3 = new Length(72,LengthUnit.INCHES);
    	Assertions.assertTrue(len1.equals(len2) && len1.equals(len3));
    }
    
    @Test 
    public void testConversion_FeetToInches() {
    	Length l1 = new Length(1, LengthUnit.FEET);
    	Assertions.assertEquals(12.0, l1.convertTo(LengthUnit.INCHES).getValue());
    }
    
    @Test 
    public void testConversion_InchesToFeet() {
    	Length l = new Length(24, LengthUnit.INCHES);
    	Assertions.assertEquals(2.0, l.convertTo(LengthUnit.FEET).getValue());
    }
    
    @Test
    public void testConversion_YardsToInches() {
    	Length l = new Length(1, LengthUnit.YARDS);
    	Assertions.assertEquals(36.0, l.convertTo(LengthUnit.INCHES).getValue());
    }
    
    @Test
    public void testConversion_InchesToYards() {
    	Length l = new Length(72, LengthUnit.INCHES);
    	Assertions.assertEquals(2.0, l.convertTo(LengthUnit.YARDS).getValue());
    }
    
    @Test 
    public void testConversion_CentimetersToInches() {
    	Length l = new Length(2.54, LengthUnit.CENTIMETERS);
    	Assertions.assertEquals(1.0, l.convertTo(LengthUnit.INCHES).getValue(), 0.0001);
    }
    
    @Test
    public void testConversion_FeetToYard() {
    	Length l = new Length(6,LengthUnit.FEET);
    	Assertions.assertEquals(2.0, l.convertTo(LengthUnit.YARDS).getValue());
    }
    
    @Test
    public void testConversion_RoundTrip_PreservesValue() {
    	 Assertions.assertEquals(12,QuantityMeasurementApp
    			 .demonstrateLengthConversion(QuantityMeasurementApp.demonstrateLengthConversion(12.0,LengthUnit.INCHES, LengthUnit.FEET).getValue(), LengthUnit.FEET, LengthUnit.INCHES).getValue());            
    }
    
    @Test 
    public void testConversion_ZeroValue() {
    	Length l1 = new Length(0, LengthUnit.FEET);
    	Assertions.assertEquals(0, l1.convertTo(LengthUnit.YARDS).getValue());
    }
    
    @Test
    public void testConversion_NegativeValue() {
    	Assertions.assertEquals(-12, QuantityMeasurementApp
    			.demonstrateLengthConversion(-1.0, LengthUnit.FEET, LengthUnit.INCHES).getValue());
    }
    
    @Test 
    public void testConversion_InvalidUnit_Throws() {
    	
    	Assertions.assertThrows(IllegalArgumentException.class, ()->{
    		QuantityMeasurementApp.demonstrateLengthConversion(null, LengthUnit.FEET, LengthUnit.CENTIMETERS);
    	});
    }
    
    @Test
    public void testConversion_NaNOrInfinite_Throws() {
    	Assertions.assertThrowsExactly(IllegalArgumentException.class, ()->{
    		QuantityMeasurementApp.demonstrateLengthConversion(Double.NaN,LengthUnit.CENTIMETERS,LengthUnit.INCHES);
    	});
    }
    
    @Test 
    public void testConversion_PrecisionTolerance() {
    	Assertions.assertEquals(1.0,QuantityMeasurementApp
    			.demonstrateLengthConversion(2.54, LengthUnit.CENTIMETERS,LengthUnit.INCHES)
    			.getValue(), 1e-6);
    }
    
    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
    	Length l1 = new Length(1, LengthUnit.FEET);
    	Length l2 = new Length(2, LengthUnit.FEET);
    	
    	Assertions.assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue());
    }
    
    @Test
    public void testAddition_SameUnit_InchPlusInch() {
    	Length l1 = new Length(6, LengthUnit.INCHES);
    	Length l2 = new Length(6, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(12.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue());
    }
    
    @Test 
    public void testAddition_CrossUnit_FeetPlusInches() {
    	Length l1 = new Length(1, LengthUnit.FEET);
    	Length l2 = new Length(12, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue());
    }
    
    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
    	Length l1 = new Length(12, LengthUnit.INCHES);
    	Length l2 = new Length(1, LengthUnit.FEET);
    	
    	Assertions.assertEquals(24.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue());
    }
    
    @Test 
    public void testAddition_CrossUnit_YardPlusFeet() {
    	Length l1 = new Length(1, LengthUnit.YARDS);
    	Length l2 = new Length(3, LengthUnit.FEET);
    	
    	Assertions.assertEquals(2, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue());
    }
    
    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
    	Length l1 = new Length(2.54, LengthUnit.CENTIMETERS);
    	Length l2 = new Length(1, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(5.08, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue(),0.001);
    }
    
    @Test
    public void testAddition_Commutativity() {
    	Length l1 = new Length(1, LengthUnit.FEET);
    	Length l2 = new Length(12, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue(), QuantityMeasurementApp.demonstrateLengthAddition(l2, l1).convertTo(LengthUnit.FEET).getValue());
    }
    
    @Test
    public void testAddition_WithZero() {
    	Length l1 = new Length(5, LengthUnit.FEET);
    	Length l2 = new Length(0, LengthUnit.FEET);
    	
    	Assertions.assertEquals(5.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue());
    }
    
    @Test
    public void testAddition_NegativeValues() {
    	Length l1 = new Length(5, LengthUnit.FEET);
    	Length l2 = new Length(-2, LengthUnit.FEET);
    	
    	Assertions.assertEquals(3.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue());
    	
    }
    
    @Test
    public void testAddition_NullSecondOperand() {
    	Length l1 = new Length(1, LengthUnit.FEET);
    	Assertions.assertThrows(IllegalArgumentException.class,()-> { QuantityMeasurementApp.demonstrateLengthAddition(l1, null);});
    }
    
    @Test
    public void testAddition_LargeValues() {
    	Length l1 = new Length(1e6, LengthUnit.FEET);
    	Length l2 = new Length(1e6, LengthUnit.FEET);
    	
    	Assertions.assertEquals(2e6, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue());
    }
    
    @Test
    public void testAddition_SmallValues() {
    	Length l1 = new Length(0.001, LengthUnit.FEET);
    	Length l2 = new Length(0.002, LengthUnit.FEET);
    	
    	Assertions.assertEquals(0.003, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2).getValue(), 0.001);
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
    	Length l1 = new Length(1, LengthUnit.FEET);
    	Length l2 = new Length(12, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.FEET).getValue());
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
    	Length l1 = new Length(1, LengthUnit.FEET);
    	Length l2 = new Length(12, LengthUnit.INCHES);
    	Assertions.assertEquals(24.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.INCHES).getValue());
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
    	Length l1 = new Length(1, LengthUnit.FEET);
    	Length l2 = new Length(12, LengthUnit.INCHES);
    	Assertions.assertEquals(0.667, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.YARDS).getValue(),0.001);
    }
    
    @Test 
    public void testAddition_ExplicitTargetUnit_Centimeters() {
    	Length l1 = new Length(1, LengthUnit.INCHES);
    	Length l2 = new Length(1, LengthUnit.INCHES);
    	Assertions.assertEquals(5.08, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.CENTIMETERS).getValue(),0.001);
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
    	Length l1 = new Length(1, LengthUnit.YARDS);
    	Length l2 = new Length(3, LengthUnit.FEET);
    	Assertions.assertEquals(2.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.YARDS).getValue());
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
    	Length l1 = new Length(2, LengthUnit.YARDS);
    	Length l2 = new Length(3, LengthUnit.FEET);
    	Assertions.assertEquals(9.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.FEET).getValue()); 
    }
    
    @Test 
    public void testAddition_ExplicitTargetUnit_Commutativity() {
    	Length l1 = new Length(1, LengthUnit.FEET);
    	Length l2 = new Length(12, LengthUnit.INCHES);
    	Assertions.assertEquals(QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.YARDS).getValue(), QuantityMeasurementApp.demonstrateLengthAddition(l2, l1, LengthUnit.YARDS).getValue()); 
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {
    	Length l1 = new Length(5, LengthUnit.FEET);
    	Length l2 = new Length(0, LengthUnit.INCHES);
    	Assertions.assertEquals(1.667, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.YARDS).getValue(),0.001); 
    }
    
    @Test 
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
    	Length l1 = new Length(5, LengthUnit.FEET);
    	Length l2 = new Length(-2, LengthUnit.FEET);
    	
    	Assertions.assertEquals(36.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.INCHES).getValue());
    }
    
    @Test 
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
    	Length l1 = new Length(5, LengthUnit.FEET);
    	Length l2 = new Length(-2, LengthUnit.FEET);
    	
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, null); });
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
    	Length l1 = new Length(1000.0, LengthUnit.FEET);
    	Length l2 = new Length(500.0, LengthUnit.FEET);
    	Assertions.assertEquals(18000.0, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.INCHES).getValue());
    }
    
    @Test 
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
    	Length l1 = new Length(12, LengthUnit.INCHES);
    	Length l2 = new Length(12, LengthUnit.INCHES);
    	
    	Assertions.assertEquals(0.667, QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, LengthUnit.YARDS).getValue(), 0.001);
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
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(new Length(1, LengthUnit.FEET),new Length(12, LengthUnit.INCHES)));
    }
    
    @Test
    public void testQuantityLengthRefactored_ConvertTo() {
    	Assertions.assertEquals(12.0,QuantityMeasurementApp.demonstrateLengthConversion(1.0, LengthUnit.FEET,LengthUnit.INCHES).getValue(),0.1);
    }
    
    @Test
    public void testQuantityLengthRefactored_Add() {
    	Assertions.assertEquals(2.0,QuantityMeasurementApp.demonstrateLengthAddition(new Length(1, LengthUnit.FEET),new Length(12, LengthUnit.INCHES), LengthUnit.FEET).getValue());
    }
    
    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {
    	Assertions.assertEquals(0.667,QuantityMeasurementApp.demonstrateLengthAddition(new Length(1, LengthUnit.FEET), new Length(12, LengthUnit.INCHES), LengthUnit.YARDS).getValue(),0.001);
    }
    
    @Test
    public void testQuantityLengthRefactored_NullUnit() {
    	Assertions.assertThrows(IllegalArgumentException.class, ()-> { new Length(1, null); });
    }
    
    @Test 
    public void testQuantityLengthRefactored_InvalidValue() {
    	Assertions.assertThrows(IllegalArgumentException.class, ()->{new Length(Double.NaN, LengthUnit.FEET); });
    }
    
    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
    	Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(1, WeightUnit.KILOGRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    }
   
    @Test
    public void testEquality_KilogramToKilogram_DifferentValue() {
    	Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(2, WeightUnit.KILOGRAM);
    	Assertions.assertFalse(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    }
    
    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
    	Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(1000, WeightUnit.GRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    }
    
    @Test
    public void testEquality_GramToKilogram_EquivalentValue() {
    	Weight w1 = new Weight(1000, WeightUnit.GRAM);
    	Weight w2 = new Weight(1, WeightUnit.KILOGRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    }
    
    @Test
    public void testEquality_NullComparison() {
    	Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
    	Assertions.assertFalse(QuantityMeasurementApp.demonstrateWeightEquality(w1, null));
    }
    @Test
    public void testEquality_SameReference() {
    	Weight w = new Weight(1, WeightUnit.KILOGRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w, w));
    }
    @Test
    public void testEquality_NullUnit() {
    	Assertions.assertThrows(IllegalArgumentException.class, ()->{
    		Weight w= new Weight(1, null);
    	});
    }
    @Test
    public void testEquality_TransitiveProperty() {
    	Weight w1= new Weight(1, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(1000, WeightUnit.GRAM);
    	
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w2, w1));
    }
    
    @Test
    public void testEquality_ZeroValue() {
    	Weight w1 = new Weight(0, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(0, WeightUnit.GRAM);
    	
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    }
    
    @Test
    public void testEquality_NegativeWeight() {
    	Weight w1 = new Weight(-1, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(-1000, WeightUnit.GRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    }
    @Test
    public void testEquality_LargeWeightValue() {
    	Weight w1 = new Weight(1000000, WeightUnit.GRAM);
    	Weight w2 = new Weight(1000, WeightUnit.KILOGRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    }
    
    @Test
    public void testEquality_SmallWeightValue() {
    	Weight w1 = new Weight(0.001, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(1, WeightUnit.GRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    }
    
    @Test
    public void testConversion_PoundToKilogram() {
    	Weight w1 = new Weight(2.20462, WeightUnit.POUND);
    	Assertions.assertEquals(1.0, QuantityMeasurementApp.demonstrateWeightConversion(w1, WeightUnit.KILOGRAM).getValue(), 0.1);
    }
    @Test 
    public void testConversion_KilogramToPound() {
    	Weight w = new Weight(1, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(2.20462, QuantityMeasurementApp.demonstrateWeightConversion(w, WeightUnit.POUND).getValue(), 0.0001);
    }
    
    @Test
    public void testConversion_SameUnit() {
    	Weight w1 = new Weight(5, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(5.0, QuantityMeasurementApp.demonstrateWeightConversion(w1, WeightUnit.KILOGRAM).getValue());
    }
    @Test
    public void testConversion_ZeroValue1() {
    	Weight w = new Weight(0, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(0, QuantityMeasurementApp.demonstrateWeightConversion(w, WeightUnit.GRAM).getValue());
    }
    
    @Test
    public void testConversion_NegativeValue1() {
    	Weight w = new Weight(-1, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(-1000.0, QuantityMeasurementApp.demonstrateWeightConversion(w, WeightUnit.GRAM).getValue());
    }
    @Test
    public void testConversion_RoundTrip() {
    	Weight w = new Weight(1.5, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(1.5, QuantityMeasurementApp.demonstrateWeightConversion(QuantityMeasurementApp.demonstrateWeightConversion(w, WeightUnit.GRAM),WeightUnit.KILOGRAM).getValue());
    }
    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
    	Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(2, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(3.0, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2).getValue());
    }
    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
    	Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(1000, WeightUnit.GRAM);
    	Assertions.assertEquals(2.0, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2).getValue());
    }
    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram() {
    	Weight w1 = new Weight(2.20462, WeightUnit.POUND);
    	Weight w2 = new Weight(1, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(4.40924, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2).getValue(), 0.00001);
    }
    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram() {
    	Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(1000, WeightUnit.GRAM);
    	Assertions.assertEquals(2000.0, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2, WeightUnit.GRAM).getValue());
    }
    @Test
    public void testAddition_Commutativity1() {
    	Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(1000, WeightUnit.GRAM);
    	Assertions.assertTrue(QuantityMeasurementApp.demonstrateWeightAddition(w1, w2).equals(QuantityMeasurementApp.demonstrateWeightAddition(w2, w1)));
    }
    
    @Test
    public void testAddition_WithZero1() {
    	Weight w1 = new Weight(5, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(0, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(5.0, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2).getValue());
    }	
    @Test
    public void testAddition_NegativeValues1() {
    	Weight w1 = new Weight(5, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(-2000, WeightUnit.GRAM);
    	Assertions.assertEquals(3.0, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2).getValue());
    }
    @Test
    public void testAddition_LargeValue1s() {
    	Weight w1 = new Weight(1e6, WeightUnit.KILOGRAM);
    	Weight w2 = new Weight(1e6, WeightUnit.KILOGRAM);
    	Assertions.assertEquals(2e6, QuantityMeasurementApp.demonstrateWeightAddition(w1, w2).getValue());
    }
}