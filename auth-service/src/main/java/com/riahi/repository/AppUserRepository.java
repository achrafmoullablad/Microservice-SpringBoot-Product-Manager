package com.riahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riahi.entities.AppUser;

/**
 * @authored by Jalel Eddine on 28 nov. 2018
 *
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String username) ;
}
