package com.example.hrms.entities.concretes;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Entity
@Table(name="job_adverts")
public class JobAdvert {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	//private int employerId;
	//private int positionId;
	//private int cityId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="salary_min")
	private int salaryMin;
	
	@Column(name="salary_max")
	private int salaryMax;
	
	@Column(name="number_of_open_position")
	private int numberOfOpenPosition;
	
	@Column(name="created_date")
	@JsonIgnore
	private LocalDate createdDate=LocalDate.now();
	
	@Column(name="application_deadline")
	private LocalDate applicationDeadline;   //utilden aldım sqlden de var ????
	
	@Column(name="is_active")
	private boolean isActive;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private Position position;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employer_id")
	//@Transient
	private Employer employer;
	
	//write getter and setter
	
	
	
	public JobAdvert() {

	}
	
	public JobAdvert(int id, String description, int salaryMin, int salaryMax, int numberOfOpenPosition,
			LocalDate applicationDeadline, boolean isActive, City city, Position position, Employer employer) {
		super();
		this.id = id;
		this.description = description;
		this.salaryMin = salaryMin;
		this.salaryMax = salaryMax;
		this.numberOfOpenPosition = numberOfOpenPosition;
		this.applicationDeadline = applicationDeadline;
		this.isActive = isActive;
		this.city = city;
		this.position = position;
		this.employer = employer;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

}
