package com.example.hrms.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.hrms.dataAccess.abstracts.UserDao;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {  //bu class'ın hangi annotation'ı ve hangi türü validate edeceği
	
	@Autowired
	private UserDao userDao;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return !userDao.existsByUsername(value);
	}

}
