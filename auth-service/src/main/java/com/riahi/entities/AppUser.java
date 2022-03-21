package com.riahi.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authored by Jalel Eddine on 28 nov. 2018
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

	@Id @GeneratedValue
	private Long id ;
	
	private String username ;
	private String password ;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRole> roles = new ArrayList<>() ;

	public AppUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
}
