package com.example.hrms.entities.concretes;





import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails, Serializable{
	
	private static final long serialVersionUID = 1L;

	public User() {};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	//@JsonIgnore                   //json gösteriminin dışında tutar.
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Transient                     //özelliği veritabanının dışında tutar.
	private String repeatedPassword;
	
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
	private Set<Role> authorities;
	
	
	
	
	
	
}
