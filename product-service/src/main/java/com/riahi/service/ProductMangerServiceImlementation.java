package com.riahi.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.riahi.entities.Categorie;
import com.riahi.entities.Produit;
import com.riahi.repository.CategorieRepository;
import com.riahi.repository.ProduitRepository;

/**
 * @author Jalel Eddine
 *
 * 25 nov. 2018
 */
@Service
@Transactional
public class ProductMangerServiceImlementation implements ProductMangerService {

	@Autowired
	public CategorieRepository categorieRepository ;
	
	@Autowired
	public ProduitRepository produitRepository ;
	
	@Override
	public Categorie enregistrerCategorie(Categorie c) {
		
		return categorieRepository.save(c);
	}

	@Override
	public void supprimerCategorie(Long idCat) {
		
		categorieRepository.deleteById(idCat);
		
	}


	@Override
	public Produit enregistrerProduit(Produit p , Long idCat ) {
		if(categorieRepository.findById(idCat)== null) {
			System.out.println("verifier categorie");
		}
		
		produitRepository.save(p) ;
		
		p.setNomCategorie(categorieRepository.getOne(idCat).getNomCategorie());
		
		 
		return p ;
	}

	@Override
	public void supprimerProduit(Long idproduit) {
		
		produitRepository.deleteById(idproduit);
	}

	@Override
	public List<Categorie> listCategorie() {
		
		return categorieRepository.findAll();
	}

	@Override
	public Categorie getCategorie(Long idCat) {
		
		return categorieRepository.getOne(idCat);
	}

	@Override
	public List<Produit> listProduit() {
		
		return produitRepository.findAll();
	}

	@Override
	public Produit getProduit(Long idProd) {
		
		return produitRepository.getOne(idProd);
	}

	@Override
	public Categorie getCatByDes(String nomcategorie) {
		
		return categorieRepository.findByNomCategorie(nomcategorie);
	}

	@Override
	public Produit getProdByDes(String designation) {
		
		return produitRepository.findByDesignation(designation);
	}



}
