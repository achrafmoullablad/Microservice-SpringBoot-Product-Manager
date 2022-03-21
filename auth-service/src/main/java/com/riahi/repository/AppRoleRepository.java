package com.riahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riahi.entities.AppRole;

/**
 * @authored by Jalel Eddine on 27 nov. 2018 
 * 
 */
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

	public AppRole findByRolename(String rolename) ;
}
