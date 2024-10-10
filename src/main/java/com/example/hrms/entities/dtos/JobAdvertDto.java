package com.example.hrms.entities.dtos;

import java.time.LocalDate;
import com.example.hrms.entities.concretes.JobAdvert;

public class JobAdvertDto {
	
	private String employerName;
	
	private String positionName;
	
	private int numberOfOpenPosition;
	
	private LocalDate createdDate;              
	
	private LocalDate applicationDeadline;
	
	public JobAdvertDto() {

	}
	
	public JobAdvertDto(String employerName, String positionName, int numberOfOpenPosition, LocalDate createdDate, LocalDate applicationDeadline) {
	    this.employerName = employerName;
	    this.positionName = positionName;
	    this.numberOfOpenPosition = numberOfOpenPosition;
	    this.createdDate = createdDate;
	    this.applicationDeadline = applicationDeadline;
	  }
	
	
	
	public JobAdvertDto(JobAdvert jobAdvert) {
		this.employerName= jobAdvert.getEmployer().getCompanyName();
		this.positionName= jobAdvert.getPosition().getName();
		this.numberOfOpenPosition= jobAdvert.getNumberOfOpenPosition();
		this.createdDate= jobAdvert.getCreatedDate();
		this.applicationDeadline= jobAdvert.getApplicationDeadline();
	}
	
	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	
	public String getPositionName() {
		return positionName;
	}
	
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	public int getNumberOfOpenPosition() {
		return numberOfOpenPosition;
	}
	
	public void setNumberOfOpenPosition(int numberOfOpenPosition) {
		this.numberOfOpenPosition = numberOfOpenPosition;
	}
	
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
	public LocalDate getApplicationDeadline() {
		return applicationDeadline;
	}
	
	public void setApplicationDeadline(LocalDate applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}
	
	
	
	
}
