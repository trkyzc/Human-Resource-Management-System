package com.example.hrms.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.UserService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.User;
import com.example.hrms.entities.dtos.LoginDto;

@RestController
@RequestMapping("hrms/api/auth")
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) {
        super();
        this.userService = userService;
	}
	
	@PostMapping("/login")
	 public Result login(@RequestBody LoginDto loginDto) {
		 
		 DataResult<String> result = userService.login(loginDto);
		 if (result.isSuccess()) {
		     System.out.println("JWT Token: " + result.getData());
		 } else {
		     System.out.println("Login failed: " + result.getMessage());
		 }
		 return result;
		}
	
//	@PostMapping("/signup")
//	public Result signUp(@RequestBody User userRegister) {
//		return userService.createUser(userRegister);
//	}

}
