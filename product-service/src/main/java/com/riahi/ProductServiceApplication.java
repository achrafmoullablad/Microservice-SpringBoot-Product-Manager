package com.riahi;

import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SystemPropertyUtils;

import com.riahi.entities.Categorie;
import com.riahi.entities.Produit;
import com.riahi.repository.CategorieRepository;
import com.riahi.repository.ProduitRepository;
import com.riahi.service.ProductMangerService;

@SpringBootApplication
@EnableEurekaClient
public class ProductServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
}

@Configuration
class InjectData implements CommandLineRunner {

	@Autowired
	public ProductMangerService productMangerService ;

	
	
	@Override
	public void run(String... args) throws Exception {
		

		Categorie cat1 = productMangerService.enregistrerCategorie(new Categorie("aaaaaaaaa")) ;
		Categorie cat2 = productMangerService.enregistrerCategorie(new Categorie("bbbbbbbbb")) ;
		
		Stream.of("PROD11111" , "PROD11222" , "PROD11333" , "PROD11444" , "PROD11555").forEach(nP ->{
			productMangerService.enregistrerProduit(new Produit(nP), cat1.getIdCategori() ) ;
					//productMangerService.enregistrerCategorie(new Categorie("aaaaaa")) ) ;
		});
		
		Stream.of("PROD22111" , "PROD22222" , "PROD22333" , "PROD22444" , "PROD22555").forEach(nP ->{
			productMangerService.enregistrerProduit(new Produit(nP),  cat2.getIdCategori() ) ;
					//productMangerService.enregistrerCategorie(new Categorie("bbbbb"))) ;
		});
		
		
		productMangerService.listCategorie()
		.forEach(l -> System.out.println( l.getIdCategori()+ " "+l.getNomCategorie()));

		
	}
	
	
}
