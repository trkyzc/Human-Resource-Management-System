	package com.example.hrms.business.concretes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.EmployerDao;
import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.concretes.Role;
import com.example.hrms.entities.dtos.EmployerRequestDto;
import com.example.hrms.mapper.EmployerMapper;

import jakarta.persistence.EntityNotFoundException;


@Service
public class EmployerManager implements EmployerService {
	
	EmployerDao employerDao;
	PasswordEncoder passwordEncoder;
	private EmployerMapper employerMapper;
	

	public EmployerManager(EmployerDao employerDao, PasswordEncoder passwordEncoder, EmployerMapper employerMapper) {
		super();
		this.employerDao = employerDao;
		this.passwordEncoder = passwordEncoder;
		this.employerMapper = employerMapper;
	}


	@Override
	public Result signUp(EmployerRequestDto employerRequestDto) {
		
		Employer employer = employerMapper.toEntity(employerRequestDto);
		employer.setPassword(passwordEncoder.encode(employer.getPassword()));
		employer.setRepeatedPassword(employer.getPassword());

		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_EMPLOYER);
		employer.setAuthorities(roles);
		
		employerDao.save(employer);
		return new SuccessResult("The employer has been successfully registered.");
		                         
	}

	
	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(employerDao.findAll(),"İşverenler başarıyla listelendi.");
	}


	@Override
	public Result deleteEmployer(int id) {
		Employer employer = employerDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Employer not found"));
		employerDao.delete(employer);
		return new SuccessResult("Employer has been deleted successfully.");
	}



}
