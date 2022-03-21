package com.riahi.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @authored by Jalel Eddine on 29 nov. 2018 
 * 
 */

@EnableWebSecurity
@Configuration
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public JwtConfig jwtConfig() {
        	return new JwtConfig();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserDetailsService userdetailsService ;
	
	@Autowired
	private JwtConfig jwtConfig ;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http
			.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				
		.and()
			.exceptionHandling().authenticationEntryPoint((req, res, e) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				
		.and()
			.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig))
			
				.authorizeRequests()
				
				.antMatchers(HttpMethod.POST,jwtConfig.getUri()).permitAll()  
				.antMatchers(HttpMethod.OPTIONS, jwtConfig.getUri()).permitAll()
				.antMatchers(HttpMethod.POST, "/api" + "/user").permitAll()
				.antMatchers("/actuator").hasAuthority("ADMIN")
				.anyRequest().authenticated() ;
		
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userdetailsService).passwordEncoder(passwordEncoder()) ;
		
	}
	
}
