package com.mathffreitas.integration.repositories;

import com.mathffreitas.integration.models.ProductExhibition;
import com.mathffreitas.integration.models.types.Category;
import com.mathffreitas.integration.repositories.utils.ProductQuery;
import com.mathffreitas.integration.utils.Constants;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlDatabase implements Database {
    private final Connection connection;

    public SqlDatabase() throws SQLException {
        connection = DriverManager.getConnection(
                Constants.JDBC_URL,
                Constants.JDBC_USER,
                Constants.JDBC_PASSWORD
        );
    }

    @Override
    public List<ProductExhibition> getProducts() {
        try {
            ResultSet result = executeQuery(ProductQuery.GetAllProducts());
            return extractResult(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProduct(ProductExhibition productExibition) {
        try {
            PreparedStatement preparedStatement = mapItemIntoStatement(ProductQuery.InsertProduct(), productExibition);
            executeQueryWithParams(preparedStatement);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateProductPriceById(Long id, BigDecimal newPrice) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductQuery.UpdateProductById(String.valueOf(id)));
            preparedStatement.setBigDecimal(1, newPrice);
            executeQueryWithParams(preparedStatement);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Optional<ProductExhibition> getProductById(Long id) {
        try {
            ResultSet resultSet = executeQuery(ProductQuery.GetProductById(String.valueOf(id)));
            List<ProductExhibition> productExhibitions = extractResult(resultSet);
            if (productExhibitions.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(productExhibitions.getFirst());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeProductById(Long id) {
        try {
           PreparedStatement preparedStatement = connection.prepareStatement(ProductQuery.DeleteProductById(String.valueOf(id)));
           executeQueryWithParams(preparedStatement);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int getSizeOfProducts() {
        try {
            ResultSet result = executeQuery(ProductQuery.ProductsCount());
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }

    private PreparedStatement mapItemIntoStatement(String sql, ProductExhibition productExibition) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, productExibition.name());
        preparedStatement.setString(2, productExibition.description());
        preparedStatement.setString(3, productExibition.category().name());
        preparedStatement.setBigDecimal(4, productExibition.price());
        preparedStatement.setBigDecimal(5, productExibition.promotionalPrice());
        return preparedStatement;
    }

    private void executeQueryWithParams(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.execute();
    }


    private ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    private List<ProductExhibition> extractResult(ResultSet resultSet) throws SQLException {
        List<ProductExhibition> products = new ArrayList<>();
        while(resultSet.next()) {
            products.add(new ProductExhibition(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    Category.valueOf(resultSet.getString("category")),
                    resultSet.getBigDecimal("price"),
                    resultSet.getBigDecimal("promotional_price")
            ));
        }
        return  products;
    }
}
