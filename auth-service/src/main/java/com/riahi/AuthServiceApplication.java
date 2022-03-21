package com.riahi;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.riahi.entities.AppRole;
import com.riahi.entities.AppUser;
import com.riahi.service.AccountService;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}

@Configuration
class InjectData implements CommandLineRunner {
	
	@Autowired
	AccountService accountService ;
	
	@Override
	public void run(String... args) throws Exception {

		
		Stream.of("ADMIN" , "USER")
			.forEach(r -> accountService.saveRole(new AppRole(r)));
		System.out.println("================== 1 =====================");
		
		accountService.saveUser(new AppUser( "admin", "1234")) ;
		accountService.saveUser(new AppUser("user" , "1234" )) ;
		
		System.out.println("================== 2 =====================");
		accountService.listUser()
		.forEach(u-> System.out.println(u.getUsername()));
		System.out.println("================== 3 =====================");
		
		accountService.addRoleToUser("admin", "ADMIN");
	
		
		
	}
	
}