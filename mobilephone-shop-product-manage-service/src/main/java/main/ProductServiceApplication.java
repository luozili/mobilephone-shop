package main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"net.hycollege.product.manage.config","net.hycollege.product.manage.controll","net.hycollege.product.manage.services.impl"})
@MapperScan("net.hycollege.product.manage.mapper")
public class ProductServiceApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
