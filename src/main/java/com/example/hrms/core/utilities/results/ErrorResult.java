package com.example.hrms.core.utilities.results;

public class ErrorResult extends Result {

	private static final long serialVersionUID = 1L;

	public ErrorResult(String message) {
		super(false, message);
	}
	
	public ErrorResult() {
		super(false);
	}

}
