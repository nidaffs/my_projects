package com.nidaff.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.nidaff")
@EnableJpaRepositories("com.nidaff.api")
@EntityScan("com.nidaff.entity")
public class AppStart {

	public static void main(String[] args) {
		SpringApplication.run(AppStart.class, args);
	}
}