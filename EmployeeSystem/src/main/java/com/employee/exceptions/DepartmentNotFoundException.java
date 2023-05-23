package com.employee.exceptions;

public class DepartmentNotFoundException extends RuntimeException {
	public DepartmentNotFoundException(String errorMessage) {
		super(errorMessage);
		
	}
 
}
