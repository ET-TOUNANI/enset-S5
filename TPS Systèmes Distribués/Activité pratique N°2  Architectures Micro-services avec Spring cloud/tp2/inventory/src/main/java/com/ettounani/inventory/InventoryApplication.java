package com.ettounani.inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ettounani.inventory.entities.Product;
import com.ettounani.inventory.repository.ProductRepository;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
@Bean
	CommandLineRunner start(ProductRepository productRepository) {
		return args -> {
				productRepository.save( new Product(null,"Computer", 8000, 3));
				productRepository.save( new Product(null,"Printer", 2000, 2));
				productRepository.save( new Product(null,"Smartphone", 6000, 5));
			productRepository.findAll().forEach(System.out::println);
		};
	}

}
