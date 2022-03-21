package com.riahi.service.broker;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.riahi.model.Categorie;
import com.riahi.model.Produit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jalel Eddine
 *
 * 27 nov. 2018
 */

@Service
@Slf4j
@EnableBinding(ApiStreamTopicConfig.class)
public class ProductServiceBroker {

	private final MessageChannel OUTPUT_ID_CAT ;
	private final MessageChannel OUTPUT_CAT ;
	
	private final MessageChannel OUTPUT_PROD ;
	private final MessageChannel OUTPUT_ID_PROD ;
	
	public ProductServiceBroker(ApiStreamTopicConfig apiStreamTopicConfig) {
		
		this.OUTPUT_ID_CAT = apiStreamTopicConfig.outboundIdCategorie() ; 
		this.OUTPUT_CAT = apiStreamTopicConfig.outboundCategorie() ;
		this.OUTPUT_ID_PROD = apiStreamTopicConfig.outboundIdProduit() ;
		this.OUTPUT_PROD = apiStreamTopicConfig.outboundProduit() ;
	}
	
	public void sendCategorie( Categorie categorie) throws Exception {
		
		try {
			
			MessageChannel messageChannel = OUTPUT_CAT ;

			messageChannel.send(MessageBuilder
					.withPayload(categorie)
						.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
							.build());
	
		}
		catch (Exception e) {
			log.error(e.toString());
		}
		
	}
	
	 public void sendIdCatToDelete(Long id) {
		
		MessageChannel messageChannel = OUTPUT_ID_CAT ;
		messageChannel.send(MessageBuilder
				.withPayload(id)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
						.build());
		
		
	}

	public void sendProduit(Produit produit) {

try {
			
			MessageChannel messageChannel = OUTPUT_PROD ;

			messageChannel.send(MessageBuilder
					.withPayload(produit)
						.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
							.build());
	
		}
		catch (Exception e) {
			log.error(e.toString());
		}
		
	}

	public void sendIdProdToDelete(Long id) {
		
		MessageChannel messageChannel = OUTPUT_ID_PROD ;
		messageChannel.send(MessageBuilder
				.withPayload(id)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
						.build());
		
	} 
}
