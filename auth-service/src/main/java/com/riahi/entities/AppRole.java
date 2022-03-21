package com.riahi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class AppRole {

	@Id @GeneratedValue
	private Long id ;
	private String rolename ;
	
	
	public AppRole(String rolename) {
		super();
		this.rolename = rolename;
	}
}
