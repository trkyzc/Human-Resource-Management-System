package com.example.hrms.security;

import java.io.IOException;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.hrms.business.abstracts.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	private JwtProvider jwtProvider;
	private UserService userService;
	
	public JwtAuthFilter(JwtProvider jwtProvider, @Lazy UserService userService) {
		this.jwtProvider = jwtProvider;
        this.userService = userService;		
	}
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String autHeader = request.getHeader("Authorization"); //Bearer token
		String token = null;
		String username = null;
		
		if (autHeader != null && autHeader.startsWith("Bearer ")) {
            token = autHeader.substring(7); //Bearer
            username = jwtProvider.extractUsername(token);  
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userService.loadUserByUsername(username);
			if (jwtProvider.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());  //kimlik doğrulama nesnesi
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);  
			}
		}
		
		filterChain.doFilter(request, response);  

	}
}
