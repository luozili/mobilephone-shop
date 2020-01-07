package net.hycollege.product.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProductManagerServicesConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
