package com.example.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.CandidateService;
import com.example.hrms.business.abstracts.UserService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.entities.concretes.Candidate;
import com.example.hrms.entities.dtos.CandidateRequestDto;
import com.example.hrms.entities.dtos.LoginDto;
import com.example.hrms.security.JwtProvider;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("hrms/api/candidates")
public class CandidateController {
	
	CandidateService candidateService;
	private final JwtProvider jwtProvider;
	private final AuthenticationManager authenticationManager;
	UserService userService;
	
	
	public CandidateController(CandidateService candidateService, JwtProvider jwtProvider, AuthenticationManager authenticationManager, UserService userService) {
		super();
		this.candidateService = candidateService;
		this.jwtProvider = jwtProvider;
		this.authenticationManager = authenticationManager;
		this.userService = userService;
	}



	@PostMapping("/signup")
	public ResponseEntity<Result> signUp(@Valid @RequestBody CandidateRequestDto candidateRequestDto) {
		
		return new ResponseEntity<>(candidateService.signUp(candidateRequestDto), HttpStatus.OK);  
		
	}
	
	@GetMapping("/getall")
	public DataResult<List<Candidate>> getAll(){	
		return candidateService.getAll();
	}
	
//	 @GetMapping("/getbyusername")
//	 public DataResult<Candidate> getByUsername(@RequestParam String username) {
//	        return candidateService.getByUsername(username);
//	    }
	 
	 @GetMapping("/getbyusername")
	 public ResponseEntity<Result> getByUsername(@RequestParam String username) {
		 return new ResponseEntity<>(candidateService.getByUsername(username), HttpStatus.OK);	
	    }

}
	 

