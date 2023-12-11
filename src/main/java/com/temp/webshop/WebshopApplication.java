package com.temp.webshop;

import com.temp.webshop.auth.entity.Customer;
import com.temp.webshop.auth.entity.Role;
import com.temp.webshop.auth.repository.CustomerRepository;
import com.temp.webshop.auth.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class WebshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}
}
