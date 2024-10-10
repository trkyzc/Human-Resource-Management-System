package com.example.hrms.entities.dtos;

import java.util.Date;




public class SchoolDto {
	
	private String name;
	private String department;
	private Date startAt;
	private Date endAt;
	
	public SchoolDto() {

	}
	
	public SchoolDto(String name, String department, Date startAt, Date endAt) {
		super();
		this.name = name;
		this.department = department;
		this.startAt = startAt;
		this.endAt = endAt;
	}
	
	//getters and setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public Date getStartAt() {
		return startAt;
	}
	
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}
	
	public Date getEndAt() {
		return endAt;
	}
	
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}
	
	

}
