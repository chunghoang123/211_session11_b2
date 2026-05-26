package org.example.b2.entity;

public class Product {

    private String id;
    private int stockQuantity;

    public Product(String id, int stockQuantity) {
        this.id = id;
        this.stockQuantity = stockQuantity;
    }

    public String getId() {
        return id;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}