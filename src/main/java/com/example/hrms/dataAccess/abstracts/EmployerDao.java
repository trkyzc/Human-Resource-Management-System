package com.example.hrms.dataAccess.abstracts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hrms.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer> {
	
	List<Employer> findByEmail(String email);

}
