package com.prgroceries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.prgroceries.entity.PRGroceryUser;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class PrGroceryAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(PrGroceryAppApplication.class, args);
	}
}
