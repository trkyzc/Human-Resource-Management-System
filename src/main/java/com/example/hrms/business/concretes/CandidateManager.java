package com.example.hrms.business.concretes;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hrms.aspect.LogExecutionTime;
import com.example.hrms.business.abstracts.CandidateService;
import com.example.hrms.core.utilities.adapters.ValidationService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.ErrorDataResult;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.CandidateDao;
import com.example.hrms.entities.concretes.Candidate;
import com.example.hrms.entities.concretes.Role;
import com.example.hrms.entities.dtos.CandidateRequestDto;
import com.example.hrms.entities.dtos.LoginDto;
import com.example.hrms.mapper.CandidateMapper;
import com.example.hrms.security.JwtProvider;

import jakarta.persistence.EntityNotFoundException;
@Service
public class CandidateManager implements CandidateService {
	
	CandidateDao candidateDao;
	ValidationService validationService;
	AuthenticationManager authenticationManager;
	JwtProvider jwtProvider;
	private final BCryptPasswordEncoder passwordEncoder;
	
	
	
	public CandidateManager(CandidateDao candidateDao, ValidationService validationService, AuthenticationManager authenticationManager, JwtProvider jwtProvider, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.candidateDao = candidateDao;
		this.validationService=validationService;
		this.authenticationManager=authenticationManager;
		this.jwtProvider=jwtProvider;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public Result signUp(CandidateRequestDto candidateRequestDto) {
		
		Candidate candidate = CandidateMapper.INSTANCE.toEntity(candidateRequestDto);
		candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));
		candidate.setRepeatedPassword(candidate.getPassword());
	
		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_CANDIDATE);
		candidate.setAuthorities(roles);
		
		candidateDao.save(candidate);
		return new SuccessResult("The candidate has been successfully registered.");
		
	}



	@Override
	public DataResult<List<Candidate>> getAll() {
		
		return new SuccessDataResult<List<Candidate>>(candidateDao.findAll(),"Adaylar başarıyla listelendi.");
	}



	@Override
	@LogExecutionTime
    public DataResult<Candidate> getByUsername(String username) {
		
		Candidate candidate = candidateDao.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı."));
		return new SuccessDataResult<>(candidate, "Başarılı.");
		
	}
    



}

