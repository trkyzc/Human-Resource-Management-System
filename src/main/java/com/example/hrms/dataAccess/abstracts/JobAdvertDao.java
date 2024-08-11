package com.example.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.hrms.entities.concretes.JobAdvert;
import com.example.hrms.entities.dtos.JobAdvertDto;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer> {
	
	
	//@Query("Select new com.example.hrms.entities.dtos.JobAdvertDto(e.companyName, p.name, j.numberOfOpenPosition, j.createdDate, j.applicationDeadline) From JobAdvert j JOIN j.position p JOIN j.employer e WHERE j.isActive=true") //DTO ile hallettim gerek kalmadı.
	List<JobAdvert> getByIsActiveTrue();
	
	@Query("Select new com.example.hrms.entities.dtos.JobAdvertDto(e.companyName, p.name, j.numberOfOpenPosition, j.createdDate, j.applicationDeadline) From JobAdvert j JOIN j.position p JOIN j.employer e WHERE j.isActive=true ORDER BY j.applicationDeadline")
	List<JobAdvertDto> getAllByOrderByApplicationDateAsc();
	
	@Query("Select new com.example.hrms.entities.dtos.JobAdvertDto(e.companyName, p.name, j.numberOfOpenPosition, j.createdDate, j.applicationDeadline) From JobAdvert j JOIN j.position p JOIN j.employer e WHERE j.isActive=true ORDER BY j.applicationDeadline DESC")
	List<JobAdvertDto> getAllByOrderByApplicationDateDesc();
	
	@Query("Select new com.example.hrms.entities.dtos.JobAdvertDto(e.companyName, p.name, j.numberOfOpenPosition, j.createdDate, j.applicationDeadline) From JobAdvert j JOIN j.position p JOIN j.employer e WHERE j.isActive=true AND e.id=:employerId")
	List<JobAdvertDto> getAllByEmployer(int employerId);
	
	@Modifying
	@Query("Update JobAdvert j SET isActive= false WHERE j.id=:id")
	void updateJobAdvertStatus(int id);
	

}
