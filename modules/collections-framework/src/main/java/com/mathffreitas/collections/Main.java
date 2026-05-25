package com.mathffreitas.collections;

import java.lang.IO;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class Main {
    public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    products.add(new Product(1, "Laptop", 999.99));
    products.add(new Product(2, "Smartphone", 499.99));
    products.add(new Product(3, "Tablet", 299.99));
    products.add(new Product(1, "Laptop", 999.99));
    IO.println(products.toString());

    Set<Product> productSet = new HashSet<>(products);
    // To use HashSet, we need to override equals() and hashCode() in the Product class
    IO.println(productSet.toString());

    HashMap<Integer, Product> productMap = new HashMap<>();
    for (Product product : products) {
        productMap.put(product.getId(), product);
    }
    IO.println(productMap.toString());
    // This will replace the existing product with id 1
    productMap.put(1, new Product(1, "Gaming Laptop", 1299.99));
    IO.println(productMap.toString());


    int size = 100000;
    Benchmark.listBenchmark(size);
    Benchmark.setBenchmark(size);
    Benchmark.mapBenchmark(size);
}
}
