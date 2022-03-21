package com.riahi.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.riahi.entities.AppUser;

/**
 * @authored by Jalel Eddine on 27 nov. 2018 
 * 
 */
@Service
public class AppUserDetailsServiceImplementation implements UserDetailsService {

	@Autowired
	private AccountService accountService ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AppUser appUser =  accountService.getUsername(username) ;
		
		if(appUser== null) throw new UsernameNotFoundException("username ou mot de passe incorrect") ;
		
		Collection<GrantedAuthority> authorities = new ArrayList<>() ;
		appUser.getRoles().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getRolename())) ;
		});
		
		return new User(username, appUser.getPassword(), authorities);
	}
}
