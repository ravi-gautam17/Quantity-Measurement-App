package com.apps.quantitymeasurement.exception;

public class QuantityMeasurementException extends RuntimeException{
	
	public static final long serialVersionUID= 1L;
	public QuantityMeasurementException(String msg) {
		super(msg);
	}
	
	public QuantityMeasurementException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
