package com.ettounani.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.ettounani.inventory.entities.Product;

@RepositoryRestController
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
 