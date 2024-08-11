package com.example.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Employer;

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
	Result signUp(@RequestBody Employer employer) {
		
		return employerService.signUp(employer);
	}
	
	@GetMapping("/getall")
	DataResult<List<Employer>> getAll(){
		return employerService.getAll();
	}
	

}
