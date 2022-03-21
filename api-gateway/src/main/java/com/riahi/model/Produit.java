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
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
	
	private Long idProduit ;
	
	private String designation ;
		
	private double prix;
	
	private String nomCategorie ;

}
