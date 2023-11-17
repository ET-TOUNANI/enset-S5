package com.ettounani.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ettounani.customer.entities.Customer;
import com.ettounani.customer.repository.CustomerRepository;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository) {
		return args -> {
			customerRepository.save(new Customer(null, "Ettounani", "tounani@gmail.com"));
			customerRepository.save(new Customer(null, "youssef", "youssef@gmail.com"));
			customerRepository.save(new Customer(null, "Abderrahmane", "abdo@gmail.com"));
			customerRepository.findAll().forEach(System.out::println);
		};
	}


}
