package com.example.phone_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.Properties;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.phone_auth.repository")
public class PhoneAuthApplication {



	public static void main(String[] args) {
		SpringApplication.run(PhoneAuthApplication.class, args);
	}

}
