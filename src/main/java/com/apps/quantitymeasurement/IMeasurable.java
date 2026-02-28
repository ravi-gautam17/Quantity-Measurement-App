package com.apps.quantitymeasurement;


@FunctionalInterface
interface SupportsArithmetic{
	boolean isSupported();
}

public interface IMeasurable {
	double getConversionFactor();
	double convertToBaseUnit(double value);
	double convertFromBaseUnit(double baseValue);
	String getUnitName();
	
	default boolean supportsArithmetic() {
		return true;
	}
	
	default void validateOperationSupport(String operation) {
		if(!supportsArithmetic()) {
			throw new UnsupportedOperationException(getUnitName()+ "category does not support "+ operation );
		}
	}
}