package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Candidate;
import com.example.hrms.entities.dtos.LoginDto;

public interface CandidateService {
	
	Result signUp(Candidate candidate);
	DataResult<List<Candidate>> getAll();
	DataResult<Candidate> getByUsername(String username);
	//DataResult<String> login(LoginDto loginDto);
}
