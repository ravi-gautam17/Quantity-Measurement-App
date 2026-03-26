package com.apps.quantitymeasurement.exception;

public class CategoryMismatchException extends RuntimeException{
	
	private static final long versionUDI = 1L;
	
	public CategoryMismatchException(String msg) {
		super(msg);
	}
}
