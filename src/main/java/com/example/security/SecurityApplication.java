package com.example.security;

import com.example.security.entities.Role;
import com.example.security.entities.User;
import com.example.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run (UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_Super_ADMIN"));
			userService.saveUser(new User(null, "user", "user", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "admin", "admin", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "user2", "user2", "1234", new ArrayList<>()));
			userService.addRoleToUser("user", "ROLE_USER");
			userService.addRoleToUser("admin", "ROLE_ADMIN");
			userService.addRoleToUser("user2", "ROLE_Super_ADMIN");


		};


	}
}