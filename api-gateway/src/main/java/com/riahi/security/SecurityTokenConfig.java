package com.riahi.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @authored by Jalel Eddine on 29 nov. 2018 
 * 
 */

@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtConfig jwtConfig;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			
		.and()
			.exceptionHandling().authenticationEntryPoint((req, res, e) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED)) 
		
		.and()
			.addFilterAfter(new JwtUsersAuthorizationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
			
			.authorizeRequests()
	
			.antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
			
			.antMatchers(HttpMethod.GET, "/api" + "/**").permitAll() 
			
			.antMatchers(HttpMethod.PUT,"/api" + "/ajouterCat").hasAuthority("ADMIN")
			
			.antMatchers(HttpMethod.PUT,"/api" + "/ajouterProd").hasAuthority("USER")
			
			.antMatchers(HttpMethod.DELETE,"/api" + "/deleteProd").hasAuthority("USER")
			
			.antMatchers(HttpMethod.DELETE,"/api" + "/deleteCat").hasAuthority("ADMIN")
			
			.anyRequest().authenticated()

			.and()
			.addFilterBefore(new JwtUsersAuthorizationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class);
		

	}

}
