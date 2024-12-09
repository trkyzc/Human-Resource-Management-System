package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.JobAdvert;
import com.example.hrms.entities.dtos.JobAdvertCreateRequest;
import com.example.hrms.entities.dtos.JobAdvertDto;


public interface JobAdvertService {
	
	DataResult<List<JobAdvertDto>> getAll();
	
	Result addAdvert(JobAdvertCreateRequest jobAdvertCreateRequest);
	
	DataResult<List<JobAdvertDto>> getByActiveJobAdverts();
	
	DataResult<List<JobAdvertDto>> getAllByOrderByApplicationDateAsc();
	
	DataResult<List<JobAdvertDto>> getAllByOrderByApplicationDateDesc();
	
	DataResult<List<JobAdvertDto>> getAllByEmployer(int employerId);
	
	Result updateJobAdvertStatus(int id);
	
	Result deleteJobAdvert(int id);

}
