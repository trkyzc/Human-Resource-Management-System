package com.example.hrms.entities.dtos;

import java.time.LocalDate;

import com.example.hrms.entities.concretes.City;
import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.concretes.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertCreateRequest {
	
	
	private String description;
	
	
	private int salaryMin;
	
	
	private int salaryMax;
	

	private int numberOfOpenPosition;
	
	
	private LocalDate applicationDeadline;   
	
	
	private boolean isActive;   //Düzenleyeceğim
	
	
	private int cityId;
	

	private int positionId;
	
	
	private int employerId;

}
