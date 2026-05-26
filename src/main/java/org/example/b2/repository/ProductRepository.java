package org.example.b2.repository;

import org.example.b2.entity.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(String id);

    Product save(Product product);
}