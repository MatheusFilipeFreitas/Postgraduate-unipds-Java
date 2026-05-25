package com.mathffreitas.integration;

import com.mathffreitas.integration.repositories.Database;
import com.mathffreitas.integration.repositories.History;
import com.mathffreitas.integration.repositories.utils.DatabaseSettings;

import java.lang.IO;
import java.util.Comparator;
public class Main {
    public static void main(String[] args) {
    Database database = createDatabase();

    database.getProductById(2L);
    database.getProductById(1L);

    History history = database.getHistory();
    history.vizualizeHistory();
    IO.println(history.getTotalRecords());

    database.removeProductById(2L);

    history.vizualizeHistory();

//    database.getProducts();
//    database.getProductByIndex(2);
//    database.removeProductByIndex(1);
//    database.getProducts();
//    IO.println("-------------");
//    database.getAllCategories();
//    IO.println("-------------");
//    database.getAllCategoriesByOrder();
//    IO.println("-------------");
//    database.getAllCategoriesByTree(null);
//    IO.println("-------------");
//    database.getAllCategoriesByTree(Comparator.comparing(models.types.Category::name));
//    IO.println("-------------");
//    database.getMapPeerCategoryQuantity();
//    database.getProductById(1L);
//    IO.println("-------------");
//    database.updateProductPriceById(2L, new BigDecimal("10.00"));
}

static Database createDatabase() {
    DatabaseSettings databaseSettings = new DatabaseSettings(false, true);
    Database database = new Database(databaseSettings);
    database.initDatabase();

    return database;
}
}
