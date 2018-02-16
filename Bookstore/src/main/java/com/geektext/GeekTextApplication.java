package com.geektext;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.geektext.domain.User;
import com.geektext.domain.security.Role;
import com.geektext.domain.security.UserRole;
import com.geektext.service.UserService;
import com.geektext.utility.SecurityUtility;

@SpringBootApplication
public class GeekTextApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(GeekTextApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("Angel");
		user1.setLastName("Cobo");
		user1.setUsername("a");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("c"));
		user1.setEmail("angcobo2@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
	}
}
