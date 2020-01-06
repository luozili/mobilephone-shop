package main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages= {"net.hycollege.order.controll", "net.hycollege.order.configuration", "net.hycollege.order.services.impl", "net.hycollege.product.services.impl"})
@MapperScan("net.hycollege.mapper")
public class OrderApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(OrderApplication.class, args);
	}

}
