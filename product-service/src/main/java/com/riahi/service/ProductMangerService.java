package com.riahi.service;

import java.util.Collection;
import java.util.List;

import com.riahi.entities.Categorie;
import com.riahi.entities.Produit;

/**
 * @author Jalel Eddine
 *
 * 25 nov. 2018
 */
public interface ProductMangerService {

	public List<Categorie> listCategorie();
	public Categorie getCategorie(Long idCat);
	public Categorie getCatByDes(String nomcategorie);
	public Categorie enregistrerCategorie(Categorie c );
	public void supprimerCategorie(Long idCat);
	
	public List<Produit> listProduit();
	public Produit getProduit(Long idProd);
	public Produit getProdByDes(String designation);
	public Produit enregistrerProduit(Produit p , Long idCat );
	public void supprimerProduit(Long idproduit);
	
}
