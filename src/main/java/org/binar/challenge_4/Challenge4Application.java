package org.binar.challenge_4;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "org.binar.challenge_4.repository")
@OpenAPIDefinition(
		info = @Info(contact =  @Contact(name = "Harisatul Aulia", email = "haris.auia404@gmail.com"),
				title = "Cinema RESTfull API", version = "1.0.0",
				description = "Simple cinema RESTful API with Spring REST. provide basic CRUD Operation for Cinema API"),
		servers = {
				@Server(url = "http://localhost:8080/cinema/api/v1", description = "Development"),
				@Server(url = "https://harisatulauliacinemaapp.up.railway.app/cinema/api/v1", description = "Production")},
		tags = {
				@Tag(name = "User", description = "This is the simple CRUD operation for User entities."),
				@Tag(name = "Movie", description = "This is the simple CRUD operation for Movie entities."),
				@Tag(name = "Schedule", description = "This is the simple CRUD operation for Schedule entities."),
				@Tag(name = "Order", description = "This is the simple CRUD operation for Order entities."),
		}
)
public class Challenge4Application {

	public static void main(String[] args) {
		SpringApplication.run(Challenge4Application.class, args);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
