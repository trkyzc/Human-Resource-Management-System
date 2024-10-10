package com.example.hrms.entities.concretes;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdverts"})
@Table(name="cities")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	//@JsonIgnore
	private int id;
	
	@Column(name="name")
	private String name;
	
	
	@OneToMany(mappedBy = "city")
	@JsonIgnore
	private List<JobAdvert> jobAdverts;
	
	public City() {

	}
	
	public City(int id, String name, List<JobAdvert> jobAdverts) {
		super();
		this.id = id;
		this.name = name;
		this.jobAdverts = jobAdverts;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<JobAdvert> getJobAdverts() {
		return jobAdverts;
	}
	
	public void setJobAdverts(List<JobAdvert> jobAdverts) {
		this.jobAdverts = jobAdverts;
	}
	
	

}
