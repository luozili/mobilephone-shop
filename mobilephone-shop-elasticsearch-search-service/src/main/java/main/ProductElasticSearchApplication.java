package main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "net.hycollege.protal.elasticsearch.dao.impl", "net.hycollege.protal.elasticsearch.service.impl",
		"net.hycollege.protal.elasticsearch.dao.controll" })
public class ProductElasticSearchApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProductElasticSearchApplication.class, args);
	}
}
