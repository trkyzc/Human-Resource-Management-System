package com.example.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hrms.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	
	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
