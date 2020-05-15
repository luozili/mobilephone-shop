package net.hycollege.admin.console.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import net.hycollege.admin.console.services.AdminService;
import net.hycollege.message.bean.Message;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Message login(String username, String password) {

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
		postParameters.add("username", username);
		postParameters.add("password", password);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
				postParameters, headers);

		Message exchange = null;
		try {
			exchange = restTemplate.postForObject("http://193.112.173.11:8082/admin/login", requestEntity, Message.class);
		} catch (RestClientException e) {
			System.out.println(e);
		}
		return exchange;
	}

}
