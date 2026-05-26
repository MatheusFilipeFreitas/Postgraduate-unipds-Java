package com.mathffreitas.integration;

import com.mathffreitas.integration.models.ProductExhibition;
import com.mathffreitas.integration.models.types.Category;
import com.mathffreitas.integration.repositories.History;
import com.mathffreitas.integration.repositories.InMemoryDatabase;
import com.mathffreitas.integration.repositories.SqlDatabase;
import com.mathffreitas.integration.repositories.utils.DatabaseSettings;

import java.lang.IO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        sqlDatabaseTest();
//        inMemoryDatabaseTest();
    }

    static void sqlDatabaseTest() throws SQLException {
        SqlDatabase sqlDatabase = new SqlDatabase();
//        sqlDatabase.removeProductById(5L);
        List<ProductExhibition> products = sqlDatabase.getProducts();
        for (ProductExhibition productExhibition : products) {
            IO.println(productExhibition.toString());
        }
        int total = sqlDatabase.getSizeOfProducts();
        IO.println(total);
//        ProductExhibition productExhibition = new ProductExhibition(
//           6L,
//           "Wateria",
//           "New product",
//            Category.SOBREMESA,
//            new BigDecimal("1.56"),
//            null
//        );
//        sqlDatabase.addProduct(productExhibition);
//        sqlDatabase.updateProductPriceById(3L, BigDecimal.valueOf(3));
        Optional<ProductExhibition> productExhibition = sqlDatabase.getProductById(3L);
        productExhibition.ifPresent(exhibition -> IO.println(exhibition.toString()));
    }

    static void inMemoryDatabaseTest() {
        InMemoryDatabase database = createDatabase();

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


    static InMemoryDatabase createDatabase() {
        DatabaseSettings databaseSettings = new DatabaseSettings(false, true);
        InMemoryDatabase database = new InMemoryDatabase(databaseSettings);
        database.initDatabase();

        return database;
    }
}
