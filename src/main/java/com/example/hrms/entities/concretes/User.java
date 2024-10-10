package com.example.hrms.entities.concretes;

import java.util.Set;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.userdetails.UserDetails;
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



@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails{
	
	public User() {};
	
	public User(int id2, String email2, String password2) {
		this.id=id2;
		this.email=email2;
		this.password=password2;
	}
	
	

	public User(int id, String username, String email, String password, String repeatedPassword,
			Set<Role> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.repeatedPassword = repeatedPassword;
		this.authorities = authorities;
	}



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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public Set<Role> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}
	
	
	
	
	
	
	
	
}
