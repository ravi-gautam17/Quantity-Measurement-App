package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}