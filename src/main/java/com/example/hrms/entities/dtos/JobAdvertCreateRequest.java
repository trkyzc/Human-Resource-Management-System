package com.example.hrms.entities.dtos;

import java.time.LocalDate;

import com.example.hrms.entities.concretes.City;
import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.concretes.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	
	
	private boolean isActive;   //Düzenleyeceğim
	
	
	private int cityId;
	

	private int positionId;
	
	
	private int employerId;

}
