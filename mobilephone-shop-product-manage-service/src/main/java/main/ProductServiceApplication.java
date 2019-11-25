package main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = { "net.hycollege.services.impl", "net.hycollege.controll" })
@MapperScan("net.hycollege.mapper")
public class ProductServiceApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
