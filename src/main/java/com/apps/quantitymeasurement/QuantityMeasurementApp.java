package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementApp {
	
	public static class Feet{
		private final double value;
		
		public Feet(double value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(this==obj) return true;
			if(obj==null || getClass() != obj.getClass()) return false;
			
			Feet other = (Feet) obj;
			return Double.compare(other.value, this.value)==0;
		}
		public double getValue() {
			return this.value;
		}
	}
	
	public static class Inches{
		private final double value;
		
		public Inches(double value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(this==obj) return true;
			if(obj==null || getClass() != obj.getClass()) return false;
			
			Inches other = (Inches) obj;
			return Double.compare(other.value, this.value)==0;
		}
		public double getValue() {
			return this.value;
		}
	}
	public static void demonstrateFeetEquality(double v1, double v2) {
        Feet f1 = new Feet(v1);
        Feet f2 = new Feet(v2);
        System.out.println("Feet Equality : "+f1.equals(f2));
    }

    public static void demonstrateInchesEquality(double v1, double v2) {
        Inches i1 = new Inches(v1);
        Inches i2 = new Inches(v2);
       System.out.println("Inche Equality : "+i1.equals(i2));
    }
    public static boolean demonstrateLengthEquality(Length len1,Length len2) {
    	return len1.equals(len2);
    }
    public static void demonstrateFeetInchComparison() {

        Length oneFoot = new Length(1, Length.LengthUnit.FEET);
        Length twelveInches = new Length(12, Length.LengthUnit.INCHES);

        System.out.println("1 Foot == 12 Inches ? : " 
                + oneFoot.equals(twelveInches));

        Length twoFeet = new Length(2, Length.LengthUnit.FEET);
        Length twentyFourInches = new Length(24, Length.LengthUnit.INCHES);

        System.out.println("2 Feet == 24 Inches ? : " 
                + twoFeet.equals(twentyFourInches));

        Length oneInch = new Length(1, Length.LengthUnit.INCHES);
        Length oneFootAgain = new Length(1, Length.LengthUnit.FEET);

        System.out.println("1 Inch == 1 Foot ? : " 
                + oneInch.equals(oneFootAgain));
    }
    public static Length demonstrateLengthComparison(Length l1, LengthUnit unit) {
    	return l1.convertTo(unit);
    }
    
    public static Length demonstrateLengthConversion(Double value,LengthUnit unit, LengthUnit toUnit) throws IllegalArgumentException {
    	if(value==null) {
    		throw new IllegalArgumentException("Not Valid Input");
    	}
    	Length l = new Length(value,unit);
    	l=l.convertTo(toUnit);
    	return l;
    }
    
    public static Length demonstrateLengthAddition(Length l1, Length l2) throws IllegalArgumentException {
    	if(l1==null || l2 == null) {
    		throw new IllegalArgumentException("Value cannot be null");
    	}
    	return l1.add(l2);
    }
	public static void main(String[] args) {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(1.0);
		System.out.println("Input: 1.0 ft and 1.0 ft");
		System.out.println("Output: Equals ("+f1.equals(f2)+")");
		
		Inches i1 = new Inches(1.0);
		Inches i2 = new Inches(1.0);
		System.out.println("Input: 1.0 inches and 1.0 inches");
		System.out.println("Output: Equals ("+i1.equals(i2)+")");
		
		demonstrateFeetEquality(1,4);
	    demonstrateInchesEquality(1, 1);
	    demonstrateFeetInchComparison();
	   System.out.println("Are lengths equals : "+ demonstrateLengthEquality(new Length(1,LengthUnit.FEET),new Length(12,Length.LengthUnit.INCHES)));
	   
	   Length l1 = new Length(1, LengthUnit.YARDS);
	   Length l2 = new Length(36, LengthUnit.INCHES);
	   System.out.println("Are lengths equal? "+ demonstrateLengthEquality(l1, l2));
	   
	   Length l3 = new Length(100, LengthUnit.CENTIMETERS);
	   Length l4 = new Length(39.3701, LengthUnit.INCHES);
	   System.out.println("Are lengths equal? "+demonstrateLengthEquality(l3, l4));
	   
	   System.out.println("Convert Feet to Inches: "+demonstrateLengthComparison(new Length(1, LengthUnit.FEET),LengthUnit.INCHES));
	   
	   System.out.println("Convert Yards to Feet: "+ demonstrateLengthComparison(new Length(3,LengthUnit.YARDS),LengthUnit.FEET));
	   
	   System.out.println("Convert Inches to Yards"+ demonstrateLengthComparison(new Length(36, LengthUnit.INCHES), LengthUnit.YARDS));
	   
	   System.out.println("Convert Centimeters to Inches: "+ demonstrateLengthComparison(new Length(1, LengthUnit.CENTIMETERS),LengthUnit.INCHES));
	   
	   System.out.println("Convert to Feet to Inches: "+ demonstrateLengthComparison(new Length(0, LengthUnit.FEET), LengthUnit.INCHES));
	   
	   System.out.println("Addition feet to feet: "+ demonstrateLengthAddition(new Length(1,LengthUnit.FEET), new Length(2,LengthUnit.FEET)));
	   
	   System.out.println("Addition feet and Inches: "+demonstrateLengthAddition(new Length(1,LengthUnit.FEET), new Length(12, LengthUnit.INCHES)));
	   
	   System.out.println("Addition Inches and feet: "+demonstrateLengthAddition(new Length(12,LengthUnit.INCHES), new Length(1,LengthUnit.FEET)));
	   
	   System.out.println("Addition Yards and feet: "+demonstrateLengthAddition(new Length(1, LengthUnit.YARDS), new Length(3, LengthUnit.FEET)));
	   
	   System.out.println("Addition Inches and Yards: "+demonstrateLengthAddition(new Length(36, LengthUnit.INCHES), new Length(1, LengthUnit.YARDS)));
	   
	   System.out.println("Addition Centimeter and Inches: "+demonstrateLengthAddition(new Length(2.54, LengthUnit.CENTIMETERS), new Length(1, LengthUnit.INCHES)));
	   
	   System.out.println("Addition Feet and Inches: "+demonstrateLengthAddition(new Length(5, LengthUnit.FEET), new Length(0, LengthUnit.INCHES)));
	   
	   System.out.println("Addition Feet and Feet: "+demonstrateLengthAddition(new Length(5, LengthUnit.FEET), new Length(-2, LengthUnit.FEET)));
	}
}