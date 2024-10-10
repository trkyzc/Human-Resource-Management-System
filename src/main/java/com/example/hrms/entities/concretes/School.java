package com.example.hrms.entities.concretes;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Table(name="schools")
@Entity
public class School {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="department")
	private String department;
	
	@OneToMany(mappedBy = "school",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//@JsonIgnore
	@JsonBackReference
	private List<Candidate> candidate;
	
	@Column(name = "start_at")
	private Date startAt;
	
	@Column(name = "end_at")
	private Date endAt;
	
	//ctors
	public School() {
		
	}
	
	public School(int id, String name, String department, List<Candidate> candidate, Date startAt, Date endAt) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.candidate = candidate;
		this.startAt = startAt;
		this.endAt = endAt;
	}
	
	//getters and setters
	
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
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public List<Candidate> getCandidate() {
		return candidate;
	}
	
	public void setCandidate(List<Candidate> candidate) {
		this.candidate = candidate;
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
