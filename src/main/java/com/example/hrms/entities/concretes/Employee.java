package com.example.hrms.entities.concretes;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@PrimaryKeyJoinColumn(name="user_id")
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User {
	
	
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	

}
