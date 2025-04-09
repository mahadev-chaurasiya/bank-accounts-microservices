package com.md.bank.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API documentation",
				description = "Bank Accounts microservices REST API Documentation",
				version = "v1",
				contact = @Contact(
					name = "Mahadev Chaurasiya",
					email = "mahadev.chaurasiya@gmail.com",
					url = 	"https://github.com/mahadev-chaurasiya/bank-accounts-microservices/"
				),
				license  = @License(
						name = "Apache 2.0",
						url = "https://github.com/mahadev-chaurasiya/bank-accounts-microservices/"

				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Bank Accounts microservices REST API Documentation",
				url = "https://github.com/mahadev-chaurasiya/bank-accounts-microservices/"
		)
)
public class BankAccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountsApplication.class, args);
	}

}
