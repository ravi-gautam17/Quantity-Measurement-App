package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp.*;
import com.apps.quantitymeasurement.Length;
import com.apps.quantitymeasurement.Length.LengthUnit;

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
    	Assertions.assertTrue(len1.equals(len2)&&len1.equals(len3));
    }
}