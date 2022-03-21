
package com.riahi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jalel Eddine
 *
 * 25 nov. 2018
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Produit {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProduit ;
	
	private String designation ;
		
	private double prix;
	
	private String nomCategorie ;

	public Produit(String designation) {
		super();
		this.designation = designation;
	}
	
	
	
}
