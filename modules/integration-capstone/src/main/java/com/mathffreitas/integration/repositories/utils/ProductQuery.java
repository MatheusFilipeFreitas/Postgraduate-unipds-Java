package com.mathffreitas.integration.repositories.utils;

import com.mathffreitas.integration.models.ProductExhibition;

import java.math.BigDecimal;

public class ProductQuery {
    public static String GetAllProducts() {
        return """
               SELECT 
                    id
                    ,name
                    ,description
                    ,category
                    ,price
                    ,promotional_price 
               FROM 
                   products
               """;
    }

    public static String GetProductById(String id) {
        return """
               SELECT 
                    id
                    ,name
                    ,description
                    ,category
                    ,price
                    ,promotional_price 
               FROM 
                   products
               WHERE
                   id =""" + id + """
                ;
                """;
    }

    public static String InsertProduct() {
        return """
               INSERT INTO products (
                   name,
                   description,
                   category,
                   price,
                   promotional_price
               )
               VALUES
               (
                ?,
                ?,
                ?::category,
                ?,
                ?
               );
               """;
    }

    public static String ProductsCount() {
        return """
               SELECT
                    COUNT(*)
               FROM
                    products;
        """;
    }

    public static String UpdateProductById(String id) {
        return """
               UPDATE 
                    products 
               SET
                    price = ?
               
               WHERE
                   id = """ + id + """ 
               ;
               """;
    }

    public static String DeleteProductById(String id) {
        return """
               DELETE
               FROM
                    products
               WHERE
                    id = """ + id + """
               ;
        """;
    }
}
