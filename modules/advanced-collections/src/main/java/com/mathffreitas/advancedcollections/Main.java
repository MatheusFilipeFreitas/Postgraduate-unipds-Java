package com.mathffreitas.advancedcollections;

import java.lang.IO;
import java.math.BigDecimal;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
    Database database = createDatabase();

    database.getProducts();
    database.getProductByIndex(2);
    database.removeProductByIndex(1);
    database.getProducts();
    IO.println("-------------");
    database.getAllCategories();
    IO.println("-------------");
    database.getAllCategoriesByOrder();
    IO.println("-------------");
    database.getAllCategoriesByTree(null);
    IO.println("-------------");
    database.getAllCategoriesByTree(Comparator.comparing(Category::name));
    IO.println("-------------");
    database.getMapPeerCategoryQuantity();
    database.getProductById(1L);
    IO.println("-------------");
    database.updateProductPriceById(2L, new BigDecimal("10.00"));
}

static Database createDatabase() {
    DatabaseSettings databaseSettings = new DatabaseSettings(true, true);
    Database database = new Database(databaseSettings);
    database.initDatabase();

    return database;
}
}
