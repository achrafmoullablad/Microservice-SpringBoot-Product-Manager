package com.riahi.integration.broker;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Jalel Eddine
 *
 * 27 nov. 2018
 */
public interface ApiStreamTopicConfig {

	String INPUT_PROD = "produit-in" ;
	
	String INPUT_ID_PROD = "id-produit-in" ;
	
	String INPUT_CAT = "categorie-in" ;
	
	String INPUT_ID_CAT = "id-categorie-in" ;
	
	@Input(INPUT_PROD)
	SubscribableChannel inboundProduit() ;
	
	@Input(INPUT_ID_PROD)
	SubscribableChannel inboundIdProduit() ;
	
	@Input(INPUT_CAT)
	SubscribableChannel inboundCategorie() ;
	
	@Input(INPUT_ID_CAT)
	SubscribableChannel inboundIdCategorie() ;
}
