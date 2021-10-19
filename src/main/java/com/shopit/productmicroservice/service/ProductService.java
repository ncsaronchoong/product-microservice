package com.shopit.productmicroservice.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
	
	private static final String FareURL = "http://localhost:8080/api/products/";
	
	public void someRestCall(String name) {

        final String uri = FareURL+ name;

        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);

	}
}


