package com.mathffreitas.reflection;

public class Main {
    public static void main(String[] args) throws Exception {
    Product product = new Product(1, "Book", 10.99);
    Reflection.explore(product);
}
}
