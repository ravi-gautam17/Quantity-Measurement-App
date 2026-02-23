package com.apps.quantitymeasurement;

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
	public static void main(String[] args) {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(1.0);
		System.out.println("Input: 1.0 ft and 1.0 ft");
		System.out.println("Output: Equals ("+f1.equals(f2)+")");
		
		Inches i1 = new Inches(1.0);
		Inches i2 = new Inches(1.0);
		System.out.println("Input: 1.0 inches and 1.0 inches");
		System.out.println("Output: Equals ("+i1.equals(i2)+")");
	}
}