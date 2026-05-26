package com.mathffreitas.integration.repositories;

import com.mathffreitas.integration.models.ProductExhibition;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface Database {
    List<ProductExhibition> getProducts();

    void addProduct(ProductExhibition productExibition);

    void updateProductPriceById(Long id, BigDecimal newPrice);

    Optional<ProductExhibition> getProductById(Long id);

    void removeProductById(Long id);

    int getSizeOfProducts();
}
