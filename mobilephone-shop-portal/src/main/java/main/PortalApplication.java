package main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "net.hycollege.portal.filter", "net.hycollege.portal.configuration",
		"net.hycollege.protal.elasticsearch.dao.impl", "net.hycollege.protal.services.impl",
		"net.hycollege.portal.controll", "net.hycollege.protal.elasticsearch.dao.impl",
		"net.hycollege.protal.elasticsearch.service.impl" })
@MapperScan("net.hycollege.portal.mapper")
public class PortalApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(PortalApplication.class, args);
	}
}
