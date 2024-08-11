package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.concretes.JobAdvert;

public interface EmployerService {
	
	Result signUp(Employer employer);
	DataResult<List<Employer>> getAll();
	

}
