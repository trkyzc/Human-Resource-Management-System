package com.example.hrms.entities.concretes;

import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@PrimaryKeyJoinColumn(name="user_id")
@Table(name="employers")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdverts"})  //Json verisi oluşturulurken bu alanları dışarıda tut.
@AllArgsConstructor
@NoArgsConstructor
public class Employer extends User {
	
	

	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="web_address")
	private String webAddress;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@OneToMany(mappedBy = "employer")
	//@JsonIgnore
	private List<JobAdvert> jobAdverts;
	
	
	
}
