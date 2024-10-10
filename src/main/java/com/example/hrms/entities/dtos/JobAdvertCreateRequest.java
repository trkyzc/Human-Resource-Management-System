package com.example.hrms.entities.dtos;

import java.time.LocalDate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;



public class JobAdvertCreateRequest {
	
	@Size(min = 10, max = 100,message = "Decription en az 10 en fazla 100 karakter arasında olmalıdır")
	private String description;
	
	@Min(value = 0)
	private int salaryMin;
	
	
	private int salaryMax;
	
	//0 dan büyük olmalı.Validation kullan.
	@Min(value = 0)
	private int numberOfOpenPosition;
	
	
	@Future //Geçmiş bir tarih olamaz.
	private LocalDate applicationDeadline;   
	
	
	private boolean active;   //Düzenleyeceğim
	
	
	private int cityId;
	

	private int positionId;
	
	
	private int employerId;
	
	public JobAdvertCreateRequest() {

	}
	
	public JobAdvertCreateRequest(String description, int salaryMin, int salaryMax, int numberOfOpenPosition,
			LocalDate applicationDeadline, boolean active, int cityId, int positionId, int employerId) {
		super();
		this.description = description;
		this.salaryMin = salaryMin;
		this.salaryMax = salaryMax;
		this.numberOfOpenPosition = numberOfOpenPosition;
		this.applicationDeadline = applicationDeadline;
		this.active = active;
		this.cityId = cityId;
		this.positionId = positionId;
		this.employerId = employerId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getSalaryMin() {
		return salaryMin;
	}
	
	public void setSalaryMin(int salaryMin) {
		this.salaryMin = salaryMin;
	}
	
	public int getSalaryMax() {
		return salaryMax;
	}
	
	public void setSalaryMax(int salaryMax) {
		this.salaryMax = salaryMax;
	}
	
	public int getNumberOfOpenPosition() {
		return numberOfOpenPosition;
	}
	
	public void setNumberOfOpenPosition(int numberOfOpenPosition) {
		this.numberOfOpenPosition = numberOfOpenPosition;
	}
	
	public LocalDate getApplicationDeadline() {
		return applicationDeadline;
	}
	
	public void setApplicationDeadline(LocalDate applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getCityId() {
		return cityId;
	}
	
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public int getPositionId() {
		return positionId;
	}
	
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	
	public int getEmployerId() {
		return employerId;
	}
	
	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}
	
	

}
