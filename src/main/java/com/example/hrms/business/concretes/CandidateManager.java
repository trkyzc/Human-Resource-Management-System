package com.example.hrms.business.concretes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
		
		
		//Spring validation sayesinde gerek kalmadı.
		
//		if(candidate.getFirstName()== null || candidate.getLastName()==null ||  
//				candidate.getEmail()==null ||  candidate.getIdentityNumber()==null || 
//				candidate.getPassword()==null ||  candidate.getRepeatedPassword()==null ||   
//				Integer.toString(candidate.getBirthYear())==null) {
//			
//			return new ErrorResult("Please fill in all fields.");		
//			
//		}
//		else {
//			if(validationService.validate(candidate.getIdentityNumber(), candidate.getFirstName(), candidate.getLastName(), candidate.getBirthYear())) {
//				
//				if(!candidateDao.findByEmailOrIdentityNumber(candidate.getEmail(),candidate.getIdentityNumber()).isEmpty()) {
//					return new ErrorResult("Bu mail veya tc daha önce kullanılmış");
//				}
//				else {
//
//					candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));
//					candidate.setRepeatedPassword(candidate.getPassword());
//					candidateDao.save(candidate);
//					return new SuccessResult("The candidate has been successfully registered.");
//				}
//				
//			}
//			else {
//				return new ErrorResult("The user could not be verified.");
//			}
//			
//		}
		
	}



	@Override
	public DataResult<List<Candidate>> getAll() {
		
		return new SuccessDataResult<List<Candidate>>(candidateDao.findAll(),"Adaylar başarıyla listelendi.");
	}



	@Override
    public DataResult<Candidate> getByUsername(String username) {
        Candidate candidate = candidateDao.findByUsername(username);
        if (candidate != null) {
            return new SuccessDataResult<>(candidate, "Başarılı.");
        } else {
            return new ErrorDataResult<>("Kullanıcı bulunamadı.");
        }
    }





}

