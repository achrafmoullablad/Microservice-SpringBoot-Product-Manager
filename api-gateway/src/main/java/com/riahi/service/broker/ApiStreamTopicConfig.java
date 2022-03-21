package com.riahi.service.broker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Jalel Eddine
 *
 * 27 nov. 2018
 */
public interface ApiStreamTopicConfig {

	String OUTPUT_PROD = "produit-out" ;
	
	String OUTPUT_ID_PROD = "produit-id-out" ;
	
	String OUTPUT_CAT = "categorie-out" ;
	
	String OUTPUT_ID_CAT = "categorie-id-out" ;
	
	@Output(OUTPUT_PROD)
	SubscribableChannel outboundProduit() ;
	
	@Output(OUTPUT_ID_PROD)
	SubscribableChannel outboundIdProduit() ;
	
	@Output(OUTPUT_CAT)
	SubscribableChannel outboundCategorie() ;
	
	@Output(OUTPUT_ID_CAT)
	SubscribableChannel outboundIdCategorie() ;
	

}
