package com.example.hrms.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
	
	private String username;
	
	private String password;
	
	private String email;

}
