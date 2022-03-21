package com.riahi.integration.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Jalel Eddine
 *
 * 25 nov. 2018
 */

import com.riahi.entities.Categorie;
import com.riahi.entities.Produit;
import com.riahi.repository.CategorieRepository;
import com.riahi.repository.ProduitRepository;
import com.riahi.service.ProductMangerService;

@RestController
@RequestMapping("/prod")
public class ProductRestController {

	@Autowired
	public ProductMangerService productMangerService ;
	
	@GetMapping("/produits")
	public List<Produit> getProduits(){
		return productMangerService.listProduit();
		
	}
	
	@GetMapping("produits/{id}")
	public Produit getProduit(@PathVariable Long id){
		return productMangerService.getProduit(id) ;
		
	}
	
	@GetMapping("/categories")
	public List<Categorie> getCategories(){
		return productMangerService.listCategorie();
		
	}
	
	@GetMapping("/categories/{id}")
	public Categorie getCategorie(@PathVariable Long id){
		return productMangerService.getCategorie(id) ;
		
	}
}
