package com.example.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;



import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
	
	@Column(name="applicationDeadline")
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
	
	

}
