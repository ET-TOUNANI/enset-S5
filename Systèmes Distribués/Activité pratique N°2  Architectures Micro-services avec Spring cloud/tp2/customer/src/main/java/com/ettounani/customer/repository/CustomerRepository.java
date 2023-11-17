package com.ettounani.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.ettounani.customer.entities.Customer;

@RepositoryRestController
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
 