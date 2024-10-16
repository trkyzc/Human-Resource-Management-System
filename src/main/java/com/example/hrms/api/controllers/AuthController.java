package com.example.hrms.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.UserService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.User;
import com.example.hrms.entities.dtos.LoginDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("hrms/api/auth")
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) {
        super();
        this.userService = userService;
	}
	
	@PostMapping("/login")
	 public ResponseEntity<Result> login(@Valid @RequestBody LoginDto loginDto) {
		 
		 return new ResponseEntity<>(userService.login(loginDto),HttpStatus.OK);
		 
		}


}
