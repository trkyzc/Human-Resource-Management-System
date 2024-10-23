package com.example.hrms.core.utilities.results;

public class SuccessResult extends Result {

	private static final long serialVersionUID = 1L;

	public SuccessResult(String message) {
		super(true, message);
	}
	
	public SuccessResult() {
		super(true);
	}
	
	

}
