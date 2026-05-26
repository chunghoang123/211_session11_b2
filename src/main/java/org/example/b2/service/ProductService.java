package org.example.b2.service;

import org.example.b2.entity.Product;
import org.example.b2.repository.ProductRepository;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public int updateStock(String productId, int quantityChange) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found with ID: " + productId));

        int newStock = product.getStockQuantity() + quantityChange;

        if (newStock < 0) {
            throw new IllegalArgumentException(
                    "Resulting stock would be negative");
        }

        product.setStockQuantity(newStock);

        productRepository.save(product);

        return newStock;
    }
}