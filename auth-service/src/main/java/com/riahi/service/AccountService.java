package com.riahi.service;

import java.util.Collection;
import java.util.List;

import com.riahi.entities.AppRole;
import com.riahi.entities.AppUser;

/**
 * @authored by Jalel Eddine on 27 nov. 2018 
 * 
 */
public interface AccountService {

	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username,String roleName);
	public AppUser getUsername(String username);
	public List<AppUser> listUser();
	//public AppUser desactiveUser(String username, boolean actived);
}
