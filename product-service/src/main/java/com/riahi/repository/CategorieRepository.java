package com.riahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riahi.entities.Categorie;

/**
 * @author Jalel Eddine
 *
 * 25 nov. 2018
 */

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	
	public Categorie findByNomCategorie(String nomCategorie) ;

}
