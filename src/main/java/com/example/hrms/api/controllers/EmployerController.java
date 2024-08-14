package com.example.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.dtos.EmployerRequestDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("hrms/api/employers")
public class EmployerController {
	
	EmployerService employerService;
	
	@Autowired
	public EmployerController(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}
	
	
	@PostMapping("/signup")
	ResponseEntity<Result> signUp(@Valid @RequestBody EmployerRequestDto employerRequestDto) {
		
		return new ResponseEntity<>(employerService.signUp(employerRequestDto),HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	ResponseEntity<DataResult<List<Employer>>> getAll(){
		return new ResponseEntity<>(employerService.getAll(),HttpStatus.OK);
	}
	

}
