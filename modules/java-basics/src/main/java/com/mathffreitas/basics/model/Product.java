package com.mathffreitas.basics.model;

public class Product {
    long id;
    String name;
    double price;

    protected Product(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
