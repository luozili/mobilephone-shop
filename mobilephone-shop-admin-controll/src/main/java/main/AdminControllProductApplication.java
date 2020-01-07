package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"net.hycollege.admin.console.util", "net.hycollege.admin.console.configuration",
		"net.hycollege.admin.console.filter", "net.hycollege.admin.console.services.impl",
		"net.hycollege.admin.console.controll" })
public class AdminControllProductApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AdminControllProductApplication.class, args);
	}

}
