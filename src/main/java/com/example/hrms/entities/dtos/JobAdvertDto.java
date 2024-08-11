package com.example.hrms.entities.dtos;

import java.time.LocalDate;
import java.util.Date;

import com.example.hrms.entities.concretes.JobAdvert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertDto {
	
	private String employerName;
	
	private String positionName;
	
	private int numberOfOpenPosition;
	
	private LocalDate createdDate;              
	
	private LocalDate applicationDate;
	
	public JobAdvertDto(JobAdvert jobAdvert) {
		this.employerName= jobAdvert.getEmployer().getCompanyName();
		this.positionName= jobAdvert.getPosition().getName();
		this.numberOfOpenPosition= jobAdvert.getNumberOfOpenPosition();
		this.createdDate= jobAdvert.getCreatedDate();
		this.applicationDate= jobAdvert.getApplicationDeadline();
	}
	
	
}
