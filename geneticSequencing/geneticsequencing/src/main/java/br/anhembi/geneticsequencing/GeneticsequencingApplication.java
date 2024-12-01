package br.anhembi.geneticsequencing;

import org.springframework.boot.SpringApplication; // Importa a classe SpringApplication, responsável por inicializar o Spring Boot e o servidor embutido.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa a anotação SpringBootApplication que facilita a configuração da aplicação.

@SpringBootApplication // Anotação que combina @Configuration, @EnableAutoConfiguration e @ComponentScan, configurando automaticamente a aplicação Spring Boot.
public class GeneticsequencingApplication {
	public static void main(String[] args) {
		SpringApplication.run(GeneticsequencingApplication.class, args); // Inicia a aplicação Spring Boot.
	}
}