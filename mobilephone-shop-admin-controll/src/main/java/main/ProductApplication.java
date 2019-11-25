package main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "net.hycollege.controll", "net.hycollege.services.impl",
		"net.hycollege.configuration", "net.hycollege.filter" })
@MapperScan("net.hycollege.mapper")
public class ProductApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProductApplication.class, args);
	}
	
}
