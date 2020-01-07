package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "net.hycollege.configuration",
		"net.hycollege.protal.elasticsearch.dao.impl", "net.hycollege.protal.elasticsearch.service.impl",
		"net.hycollege.protal.elasticsearch.controll" })
public class ProductElasticSearchApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProductElasticSearchApplication.class, args);
	}
}
