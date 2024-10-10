package com.example.hrms.entities.concretes;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name="candidates")
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
	
	public Candidate() {
	
	}

	public Candidate(int id, String email, String password, String firstName, String lastName, String identityNumber,
			int birthYear, School school) {
		super(id, email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.identityNumber = identityNumber;
		this.birthYear = birthYear;
		this.school = school;
	}
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getIdentityNumber() {
		return identityNumber;
	}


	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}


	public int getBirthYear() {
		return birthYear;
	}


	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}


	public School getSchool() {
		return school;
	}


	public void setSchool(School school) {
		this.school = school;
	}
	
	

}
