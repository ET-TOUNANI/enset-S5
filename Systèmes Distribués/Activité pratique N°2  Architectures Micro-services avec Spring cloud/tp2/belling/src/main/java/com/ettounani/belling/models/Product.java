package com.ettounani.belling.models;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private double price;
    private double quantity;
}
