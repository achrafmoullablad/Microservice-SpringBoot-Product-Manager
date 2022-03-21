package com.riahi.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riahi.entities.AppUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @authored by Jalel Eddine on 29 nov. 2018 
 * 
 */
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

private AuthenticationManager authenticationManager ;
	
	private final JwtConfig jwtConfig ;
	
	@Autowired
	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig ) {
		this.authenticationManager =authenticationManager ;
		this.jwtConfig = jwtConfig ;
		
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST")); 
	}
	
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
			
			try {
				
				AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
				
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						appUser.getUsername(), appUser.getPassword()) ;
				
				return authenticationManager.authenticate(authenticationToken);
				
			} catch (IOException e) {
				
				throw new RuntimeException(e) ;
			}
			
	}
	
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		User userSpring = (User) authResult.getPrincipal() ;
		
		String token = Jwts.builder()
				.setSubject(userSpring.getUsername())
					.setExpiration(new 
							Date(System.currentTimeMillis()+jwtConfig.getExpiration()*1000))
						
							.signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret().getBytes())
							.claim("roles", userSpring.getAuthorities())
								.compact();
		System.out.println("------------------------------------------------------");
		System.out.println(token);
		System.out.println("------------------------------------------------------");
		
		response.addHeader(jwtConfig.getHeader()+"", jwtConfig.getPrefix() + token);
	}
}
