package com.riahi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jalel Eddine
 *
 * 27 nov. 2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {

	private Long idCategori;
	  
	private String nomCategorie;
	
	private String description;
	
	private String nomPhoto;
}
