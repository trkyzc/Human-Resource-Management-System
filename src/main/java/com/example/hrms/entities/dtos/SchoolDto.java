package com.example.hrms.entities.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class SchoolDto {
	
	private String name;
	private String department;
	private Date startAt;
	private Date endAt;

}
