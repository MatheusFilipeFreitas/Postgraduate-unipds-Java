package com.mathffreitas.repository;

import java.lang.IO;
public class Main {
    public static void main(String[] args) {
    ProductRepository repository = new ProductRepositoryImpl();
    repository.init();

    int targetId = 10;

    Product p = repository.findById(targetId)
            .orElseThrow(() -> new RuntimeException("Product not found"));

    IO.println(p.toString());
}
}
