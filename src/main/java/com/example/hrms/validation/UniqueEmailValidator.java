package com.example.hrms.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.hrms.dataAccess.abstracts.UserDao;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	
	@Autowired
	private UserDao userDao;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return !userDao.existsByEmail(value);
	}

}
