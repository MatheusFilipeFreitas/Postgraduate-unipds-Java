package com.mathffreitas.collections;

import java.lang.IO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Benchmark {

    public static void listBenchmark(int size) {
        throwIfExceedsSize(size);

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int fixedIndex = i + 1;
            products.add(new Product(fixedIndex, "Product " + fixedIndex, fixedIndex * 10.0));
        }

        benchmarch(size, products);
    }

    public static void setBenchmark(int size) {
        throwIfExceedsSize(size);

        Set<Product> productSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            int fixedIndex = i + 1;
            productSet.add(new Product(fixedIndex, "Product " + fixedIndex, fixedIndex * 10.0));
        }

        benchmarch(size, productSet);
    }

    public static void mapBenchmark(int size) {
        throwIfExceedsSize(size);

        HashMap<Integer, Product> productMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int fixedIndex = i + 1;
            productMap.put(fixedIndex, new Product(fixedIndex, "Product " + fixedIndex, fixedIndex * 10.0));
        }

        benchmarch(size, productMap);
    }

    private static void throwIfExceedsSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        if (size > 100000) {
            throw new IllegalArgumentException("Size must be less than or equal to 100000");
        }
    }

    private static void benchmarch(int size, Collection<Product> products) {
        int targetItemIndex = size - 1;
        long init, end, elapsedTime;
        init = System.currentTimeMillis();
        for (int count = 1; count <= 1000; count++) {
            for (Product product : products) {
                if (product.getId() == targetItemIndex)
                    break;
            }
        }
        end = System.currentTimeMillis();
        elapsedTime = end - init;
        IO.println("[" + products.getClass().getSimpleName() + "] Time taken to access the last item: " + elapsedTime + " ms");
    }

    private static void benchmarch(int size, Map<Integer, Product> productMap) {
        int targetItemIndex = size - 1;
        long init, end, elapsedTime;
        init = System.currentTimeMillis();
        for (int count = 1; count <= 1000; count++) {
            productMap.get(targetItemIndex);
        }
        end = System.currentTimeMillis();
        elapsedTime = end - init;
        IO.println("[" + productMap.getClass().getSimpleName() + "] Time taken to access the last item: " + elapsedTime + " ms");
    }
}
