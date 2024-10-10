package com.example.hrms.entities.concretes;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@PrimaryKeyJoinColumn(name="user_id")
@Table(name="employers")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdverts"})  //Json verisi oluşturulurken bu alanları dışarıda tut.
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
	
	public Employer() {

	}
	
	public Employer(int id, String email, String password, String companyName, String webAddress, String phoneNumber) {
		super(id, email, password);
		this.companyName = companyName;
		this.webAddress = webAddress;
		this.phoneNumber = phoneNumber;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getWebAddress() {
		return webAddress;
	}
	
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public List<JobAdvert> getJobAdverts() {
		return jobAdverts;
	}
	
	public void setJobAdverts(List<JobAdvert> jobAdverts) {
		this.jobAdverts = jobAdverts;
	}
	
	
	
	
	
}
