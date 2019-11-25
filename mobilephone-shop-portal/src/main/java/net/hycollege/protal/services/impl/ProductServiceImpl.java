package net.hycollege.protal.services.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import net.hycollege.message.bean.ESMssage;
import net.hycollege.protal.services.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ESMssage searchProduct(String productName, int pageIndex, int size) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("productName", productName);
			body.add("pageIndex", pageIndex + "");
			body.add("size", size + "");
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, header);
			return restTemplate.postForObject("http://localhost:8084/product/search", request, ESMssage.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
