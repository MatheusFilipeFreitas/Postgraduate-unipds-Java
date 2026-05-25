package com.mathffreitas.inheritance.core;

public class Product {
    private int code;
    private float price;
    private String description;
    private int quantityInStock;

    public Product(int code, float price, String description, int quantityInStock) {
        this.setCode(code);
        this.setPrice(price);
        this.setDescription(description);
        this.setQuantityInStock(quantityInStock);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        if (code < 0) {
            throw new IllegalArgumentException("Code cannot be negative");
        }
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        if  (quantityInStock < 0) {
            throw new IllegalArgumentException("Quantity in stock cannot be negative");
        }
        this.quantityInStock = quantityInStock;
    }
}
