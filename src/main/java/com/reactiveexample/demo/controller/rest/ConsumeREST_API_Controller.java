package com.reactiveexample.demo.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/consume")
public class ConsumeREST_API_Controller {

		@GetMapping
	   public String getNameAndCiyAsJson() {
	        RestTemplate restTemplate = new RestTemplate();

	        String resourceUrl
	          = "https://mocki.io/v1/d4867d8b-b5d5-4a48-a4ab-79131b5809b8";

	        // Fetch JSON response as String wrapped in ResponseEntity
	        ResponseEntity<String> response
	          = restTemplate.getForEntity(resourceUrl, String.class);
	        
	        String productsJson = response.getBody();
	        
	        System.out.println(productsJson);
	        
	        return productsJson;
	    }
}
 