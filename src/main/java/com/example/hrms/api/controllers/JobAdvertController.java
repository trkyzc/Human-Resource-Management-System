package com.example.hrms.api.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.JobAdvertService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.entities.concretes.JobAdvert;
import com.example.hrms.entities.dtos.JobAdvertCreateRequest;
import com.example.hrms.entities.dtos.JobAdvertDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("hrms/api/jobAdverts")
public class JobAdvertController {
	
	JobAdvertService jobAdvertService;
	
	@Autowired
	public JobAdvertController(JobAdvertService jobAdvertService) {
		super();
		this.jobAdvertService = jobAdvertService;
	}


	@PostMapping("/addAdvert")
	Result addAdvert(@Valid @RequestBody JobAdvertCreateRequest jobAdvertCreateRequest) {
		
		return jobAdvertService.addAdvert(jobAdvertCreateRequest);
		
	}
	
	@GetMapping("/getByActiveJobAdverts")
	DataResult<List<JobAdvertDto>> getByActiveJobAdverts() {
		
		return jobAdvertService.getByActiveJobAdverts();
	} 
	
	@GetMapping("/getAllByOrderByApplicationDateAsc")
	DataResult<List<JobAdvertDto>> getAllByOrderByApplicationDateAsc() {
		
		return jobAdvertService.getAllByOrderByApplicationDateAsc();
	} 
	
	@GetMapping("/getAllByOrderByApplicationDateDesc")
	DataResult<List<JobAdvertDto>> getAllByOrderByApplicationDateDesc(){
		return jobAdvertService.getAllByOrderByApplicationDateDesc();
	}
	
	@GetMapping("/getAllByEmployer")
	DataResult<List<JobAdvertDto>> getAllByEmployer(@Valid @RequestParam int employerId) {
		
		return jobAdvertService.getAllByEmployer(employerId);
	}
	
	@PutMapping("/updateJobAdvertStatus")
	Result updateJobAdvertStatus(@RequestParam int id) {
		
		return jobAdvertService.updateJobAdvertStatus(id);
	}
	
	
	
}
