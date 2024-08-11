package com.example.hrms.business.abstracts;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.CandidateDao;
import com.example.hrms.dataAccess.abstracts.EmployerDao;
import com.example.hrms.dataAccess.abstracts.UserDao;
import com.example.hrms.entities.concretes.User;
import com.example.hrms.entities.dtos.LoginDto;
import com.example.hrms.security.JwtProvider;

import lombok.extern.slf4j.Slf4j;

@Service
//log
@Slf4j
public class UserService implements UserDetailsService {
	
	private final UserDao userDao;
	AuthenticationManager authenticationManager;
	JwtProvider jwtProvider;
	
	
	public UserService(UserDao userDao, @Lazy AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
		this.userDao = userDao;
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
	}
	

	
	
//	public Result createUser(User userRegister) {    //Dto ile yapılacak.  KALKACAK.
//		userDao.save(userRegister);
//		return new SuccessResult("User created.");
//	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userDao.findByUsername(username);
	}




	public DataResult<String> login(LoginDto loginDto) {
		//LOG
		log.info("logine girildi");
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		if (authentication.isAuthenticated()) {
			log.info("isauthenticate girildi");
            return new SuccessDataResult<>(jwtProvider.generateToken(loginDto.getUsername()), "Başarılı.");
		}
		
		throw new UsernameNotFoundException("Invalid username {} " + loginDto.getUsername());
	}

}

