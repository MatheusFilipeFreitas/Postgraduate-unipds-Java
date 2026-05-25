package com.mathffreitas.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import java.util.Comparator;
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> products;

    public void init() {
        products = new ArrayList<>(){{
            add(new Product(1, "Laptop", 999.99));
            add(new Product(2, "Smartphone", 499.99));
            add(new Product(3, "Tablet", 299.99));
            add(new Product(4, "Mouse", 59.99));
        }};
    }

    public Optional<Product> findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return Optional.of(product);
            }
        }
        return Optional.ofNullable(null);
    }

    public List<Product> sortByName() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .toList();
    }

    public List<Product> sortByNameReverse() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName).reversed())
                .toList();
    }

    public List<Product> getMutableSortedByName() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }
}
