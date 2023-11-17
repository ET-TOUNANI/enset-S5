package com.ettounani.belling.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ettounani.belling.models.Product;


@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductItemRestClient { 
    @GetMapping(path = "/products")
    PagedModel<Product> findAll(@RequestParam(value = "page") int page,@RequestParam(value="size") int size);
    @GetMapping(path = "/products/{id}")
    Product findProductById(@RequestParam(value = "id") Long id);
}