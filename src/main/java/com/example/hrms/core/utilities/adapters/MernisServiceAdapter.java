package com.example.hrms.core.utilities.adapters;

import org.springframework.stereotype.Service;

import com.example.hrms.mernisService.MernisService;

@Service
public class MernisServiceAdapter implements ValidationService {
	
	MernisService mernisService;
	

	public MernisServiceAdapter(MernisService mernisService) {
		super();
		this.mernisService = mernisService;
	}


	@Override
	public boolean validate(String nationalId, String firstName, String lastName, int birthYear) {
		
		return mernisService.validate(nationalId, firstName, lastName, birthYear);	
	}

}
