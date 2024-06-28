package com.challenge.forohub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ForohubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForohubApplication.class, args);
	}

}
