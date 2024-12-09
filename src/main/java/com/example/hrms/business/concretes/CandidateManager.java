package com.example.hrms.business.concretes;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hrms.aspect.LogExecutionTime;
import com.example.hrms.business.abstracts.CandidateService;
import com.example.hrms.cache.CacheableConfig;
import com.example.hrms.cache.CacheableConfig.CacheTarget;
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
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class CandidateManager implements CandidateService {
	
	CandidateDao candidateDao;
	ValidationService validationService;
	AuthenticationManager authenticationManager;
	JwtProvider jwtProvider;
	private final BCryptPasswordEncoder passwordEncoder;
	private CandidateMapper candidateMapper;
	
	
	
	public CandidateManager(CandidateDao candidateDao, ValidationService validationService, AuthenticationManager authenticationManager, JwtProvider jwtProvider, BCryptPasswordEncoder passwordEncoder, CandidateMapper candidateMapper) {
		super();
		this.candidateDao = candidateDao;
		this.validationService=validationService;
		this.authenticationManager=authenticationManager;
		this.jwtProvider=jwtProvider;
		this.passwordEncoder = passwordEncoder;
		this.candidateMapper=candidateMapper;
	}



	@Override
	@Caching(evict =  @CacheEvict(value = "candidates", key = "'allCandidates'",cacheResolver ="cacheResolver"))
	public DataResult<Candidate> signUp(CandidateRequestDto candidateRequestDto) {
		
		Candidate candidate = candidateMapper.toEntity(candidateRequestDto);
		candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));
		candidate.setRepeatedPassword(candidate.getPassword());
		
		//System.out.println(candidate.getSchools());
	
		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_CANDIDATE);
		candidate.setAuthorities(roles);
		
		
		candidateDao.save(candidate);
		return new SuccessDataResult<>(candidate,"The candidate has been successfully registered.");
		
		//@CachePut(value = "candidates", key = "'allCandidates'",cacheResolver ="cacheResolver") için gerekli kodlar
//		List<Candidate> allCandidates = candidateDao.findAll();  
//	    return new SuccessDataResult<>(allCandidates, "The candidate has been successfully registered.");
//		
	}



	@Override
	@CacheableConfig(cacheTarget = CacheTarget.MEMORY_AND_SHARED)
	@Cacheable(cacheNames = "candidates", key = "'allCandidates'", unless = "#result==null",cacheResolver = "cacheResolver")
	public DataResult<List<Candidate>> getAll() {
		
		return new SuccessDataResult<List<Candidate>>(candidateDao.findAll(),"Adaylar başarıyla listelendi.");
	}



	@Override
	@LogExecutionTime
	@CacheableConfig(cacheTarget = CacheTarget.MEMORY_AND_SHARED)
	@Cacheable(value = "candidateByUsername", key = "#username",cacheResolver = "cacheResolver")
    public DataResult<Candidate> getByUsername(String username) {
		
		log.info("CandidateManager.getByUsername() metodu çalıştı."); //cache kontrol
		
		Candidate candidate = candidateDao.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı."));
		return new SuccessDataResult<>(candidate, "Başarılı.");
		
	}



	@Override
	@Caching(evict = { @CacheEvict(value = "candidates", allEntries = true,cacheResolver = "cacheResolver"),
			@CacheEvict(value = "candidateByUsername", allEntries=true, cacheResolver = "cacheResolver") })
	public Result deleteCandidate(int id) {
		Optional<Candidate> candidate = candidateDao.findById(id);
		if(candidate==null) {
			throw new EntityNotFoundException("Böyle bir aday yok");
		}
		candidateDao.deleteById(id);
		return new SuccessResult("Aday başarı ile silindi.") ;	
	}



	@Override
	@Caching(evict = { @CacheEvict(value = "candidates", allEntries = true,cacheResolver = "cacheResolver"),
			@CacheEvict(value = "candidateByUsername", allEntries = true, cacheResolver = "cacheResolver") })
	public DataResult<Candidate> updateCandidate(int id, CandidateRequestDto candidateRequestDto) {
		
		Candidate candidate = candidateDao.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Böyle bir aday yok"));
		
		candidateMapper.updateCandidateFromDto(candidateRequestDto, candidate);
		

	    // Parola güncellemesi gerekiyorsa
	    if (candidateRequestDto.getPassword() != null && !candidateRequestDto.getPassword().isBlank()) {
	        candidate.setPassword(passwordEncoder.encode(candidateRequestDto.getPassword()));
	        candidate.setRepeatedPassword(candidate.getPassword());
	    }
	  
	    candidateDao.save(candidate);
	
	    return new SuccessDataResult<>(candidate, "Aday başarıyla güncellendi.");
	
//		candidate.get().setPassword(passwordEncoder.encode(candidateRequestDto.getPassword()));
//		candidate.get().setRepeatedPassword(candidateRequestDto.getPassword());
		
		
	}
    



}

