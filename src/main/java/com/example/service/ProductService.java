package com.example.service;

import com.example.entity.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService  {
    List<Product> findAll();

    Product createProduct(Product product);

    Optional<Product> findById(int id);

    Product updateProduct(Product product);
}
