package com.example.hrms.api.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	ResponseEntity<Result> addAdvert(@Valid @RequestBody JobAdvertCreateRequest jobAdvertCreateRequest) {
		
		return new ResponseEntity<> (jobAdvertService.addAdvert(jobAdvertCreateRequest), HttpStatus.OK);
		
	}
	
	@GetMapping("/getByActiveJobAdverts")
	ResponseEntity<DataResult<List<JobAdvertDto>>> getByActiveJobAdverts() {
		
		return new ResponseEntity<> (jobAdvertService.getByActiveJobAdverts(),HttpStatus.OK);
	} 
	
	@GetMapping("/getAllByOrderByApplicationDateAsc")
	ResponseEntity<DataResult<List<JobAdvertDto>>> getAllByOrderByApplicationDateAsc() {
		
		return new ResponseEntity<>(jobAdvertService.getAllByOrderByApplicationDateAsc(),HttpStatus.OK);
	} 
	
	@GetMapping("/getAllByOrderByApplicationDateDesc")
	ResponseEntity<DataResult<List<JobAdvertDto>>> getAllByOrderByApplicationDateDesc(){
		return new ResponseEntity<> (jobAdvertService.getAllByOrderByApplicationDateDesc(),HttpStatus.OK);
	}
	
	@GetMapping("/getAllByEmployer")
	ResponseEntity<DataResult<List<JobAdvertDto>>> getAllByEmployer(@Valid @RequestParam int employerId) {
		
		return new ResponseEntity<> (jobAdvertService.getAllByEmployer(employerId),HttpStatus.OK);
	}
	
	@PutMapping("/updateJobAdvertStatus")
	ResponseEntity<Result> updateJobAdvertStatus(@RequestParam int id) {
		
		return new ResponseEntity<> (jobAdvertService.updateJobAdvertStatus(id),HttpStatus.OK);
	}
	
	
	
}
