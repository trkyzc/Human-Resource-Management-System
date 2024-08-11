package com.example.hrms.core.utilities.adapters;

public interface ValidationService {
	
	boolean validate(String nationalId,String firstName, String lastName, int birthYear);

}
