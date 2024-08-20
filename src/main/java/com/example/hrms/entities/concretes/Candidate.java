package com.example.hrms.entities.concretes;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="candidates")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class Candidate extends User {
	
	
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="identity_number")
	private String identityNumber;
	
	@Column(name="birth_year")
	private int birthYear;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="school_id")
	@JsonManagedReference
	private School school;

}
