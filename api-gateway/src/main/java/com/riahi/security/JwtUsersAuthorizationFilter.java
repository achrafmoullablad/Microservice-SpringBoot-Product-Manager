package com.riahi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @authored by Jalel Eddine on 29 nov. 2018 
 * 
 */

public class JwtUsersAuthorizationFilter extends OncePerRequestFilter {

	
	private final JwtConfig jwtConfig;

	public Claims claims;
	
	public JwtUsersAuthorizationFilter(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		
 
	   response.addHeader("Access-Control-Allow-Origin","*");
	   
	   response.addHeader("Access-Control-Allow-Headers", 
				" Origin,Accept,X-Requested-With,Content-Type,"
				+ "Access-Control-Request-Method,"
				+ "Access-Control-Request-Headers,"
				+ "Authorization");
	
	
	    response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,"
			+"Access-Control-Allow-Credentiels,Authorization");
		

		if(request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		}
			
			
			// 1. get the authentication header. Tokens are supposed to be passed in the authentication header
			String header = request.getHeader(jwtConfig.getHeader()); 
			// 2. validate the header and check the prefix
			if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
				chain.doFilter(request, response);  		// If not valid, go to the next filter.
				return;
			}
		
			
		
			// 3. Get the token
			String token = header.replace(jwtConfig.getPrefix(),"");
			
			System.out.println(token);
			
			
			
			try {
				
				claims = Jwts.parser()
						.setSigningKey(jwtConfig.getSecret().getBytes())
						.parseClaimsJws(token)
						.getBody();
				
				System.out.println(claims);
				
				String username = claims.getSubject();
				
				System.out.println(username);
				
				if(username != null) {
					@SuppressWarnings("unchecked")
					ArrayList<HashMap<String, String>> roles=(ArrayList<HashMap<String, String>>) claims.get("roles");
				
					System.out.println(roles);
					
					
					roles.forEach(r-> System.out.println(r.get("authority")));
					
				
					
					Collection<GrantedAuthority> authorities=new ArrayList<>();
					
					
					roles.forEach(r->{
						authorities.add(new SimpleGrantedAuthority(r.get("authority")));
					});
					
					System.out.println(authorities);
					
					System.out.println("===============================================================");
					
					UsernamePasswordAuthenticationToken authenticationToken= 
							new UsernamePasswordAuthenticationToken(username, null, authorities);
					
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
					System.out.println("===============================================================");
					}
				
				
				
			} catch (Exception e) {
				SecurityContextHolder.clearContext();
			}
			
			
			chain.doFilter(request, response);	
			
	} 

}
