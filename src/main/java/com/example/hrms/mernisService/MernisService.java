package com.example.hrms.mernisService;

import org.springframework.stereotype.Service;

@Service
public class MernisService {
	
	public boolean validate(String nationalId,String firstName, String lastName, int birthYear) {
		System.out.println(firstName + " is a valid person.");
		return true;
	}


}
