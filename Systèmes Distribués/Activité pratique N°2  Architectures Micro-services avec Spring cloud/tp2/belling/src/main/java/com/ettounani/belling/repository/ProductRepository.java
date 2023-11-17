package com.ettounani.belling.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ettounani.belling.entities.ProductItem;

@RepositoryRestResource
public interface ProductRepository  extends JpaRepository <ProductItem, Long>{
    public Collection<ProductItem> findByBillId(Long id);
}
