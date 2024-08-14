package com.example.hrms.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hrms.entities.concretes.Candidate;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {
	
	List<Candidate> findByEmailOrIdentityNumber(String email, String identityNumber);
	
	//User tablosundaki username alanına göre arama yapacak. 
	

    Optional<Candidate> findByUsername(String username); 
	

}
