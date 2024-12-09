package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.concretes.JobAdvert;
import com.example.hrms.entities.dtos.EmployerRequestDto;

public interface EmployerService {
	
	Result signUp(EmployerRequestDto employerRequestDto);
	DataResult<List<Employer>> getAll();
	Result deleteEmployer(int id);
	

}
