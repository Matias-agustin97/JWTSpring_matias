package com.raven.Jasonweb;

import com.raven.Jasonweb.model.Role;
import com.raven.Jasonweb.model.Usuario;
import com.raven.Jasonweb.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JasonwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JasonwebApplication.class, args);
	}



	//In our security file we need an instance of PasswordEncoder, so we use the bean annotation to tell
	//spring " Hey, make one of this object"
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	//Everything inside this method, will run after the applciation starts

	//We tell spring, as soon as you load up, make a Object CommandLineRunner
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRol(new Role(null,"ROLE:USER"));
			userService.saveRol(new Role(null,"ROLE:MANAGER"));
			userService.saveRol(new Role(null,"ROLE:ADMIN"));
			userService.saveRol(new Role(null,"ROLE:SUPERUSER"));

			userService.saveUsuario(new Usuario(null,"Matias Cuervo","matias","123",new ArrayList<>()));
			userService.saveUsuario(new Usuario(null,"Artyomcito","artyom","123",new ArrayList<>()));
			userService.saveUsuario(new Usuario(null,"Tramontana","banana","123",new ArrayList<>()));
			userService.saveUsuario(new Usuario(null,"Dawn james","albi","123",new ArrayList<>()));

			//CAPO, FIJATE QEU DICE USERNAME, NO NAME PELOTUDO
			//A VER SI TE VOLVES A ENTRAR EN PANICO AL DOPE PELOTUDO
			userService.addRoleToUser("matias","ROLE:SUPERUSER");
			userService.addRoleToUser("albi","ROLE:ADMIN");
			userService.addRoleToUser("artyom","ROLE:MANAGER");
			userService.addRoleToUser("banana","ROLE:USER");
		};
	}
}
