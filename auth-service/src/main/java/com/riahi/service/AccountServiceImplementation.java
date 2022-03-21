package com.riahi.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.riahi.entities.AppRole;
import com.riahi.entities.AppUser;
import com.riahi.repository.AppRoleRepository;
import com.riahi.repository.AppUserRepository;

/**
 * @authored by Jalel Eddine on 27 nov. 2018 
 * 
 */
@Service
@Transactional
public class AccountServiceImplementation implements AccountService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;  
	
	@Autowired
	private AppUserRepository appUserRepository  ;
	
	@Autowired
	private AppRoleRepository appRoleRepository ;
	
	@Override
	public AppUser saveUser(AppUser user) {

		String hashPW=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		user.getRoles().add(appRoleRepository.findByRolename("USER")) ;
		return appUserRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		
		return appRoleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		
		AppRole role =  appRoleRepository.findByRolename(roleName) ;
		
		AppUser appUser = appUserRepository.findByUsername(username) ;
	
		appUser.getRoles().add(role);
	}

	@Override
	public AppUser getUsername(String username) {
		
		return appUserRepository.findByUsername(username);
	}

	@Override
	public List<AppUser> listUser() {
		
		return appUserRepository.findAll();
		}

}
