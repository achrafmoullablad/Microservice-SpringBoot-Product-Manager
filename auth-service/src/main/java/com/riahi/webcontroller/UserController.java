package com.riahi.webcontroller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.riahi.entities.AppUser;
import com.riahi.service.AccountService;

/**
 * @authored by Jalel Eddine on 28 nov. 2018 
 * 
 */
public class UserController {

	@Autowired
	public AccountService accountService ;
	
	@GetMapping("/users")
	public Collection<AppUser> listeUser(){
		return accountService.listUser() ;
	}
	
	@PostMapping("/user")
	public AppUser saveUser(@RequestBody AppUser appUser) {
		return accountService.saveUser(appUser) ;
	}
}
