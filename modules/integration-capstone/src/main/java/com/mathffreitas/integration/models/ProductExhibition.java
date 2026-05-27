package com.mathffreitas.integration.models;

import com.mathffreitas.integration.models.types.Category;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProductExhibition(
        Long id,
        String name,
        String description,
        Category category,
        BigDecimal price,
        BigDecimal promotionalPrice
    ) implements Serializable {

    public ProductExhibition setNewPrice(BigDecimal newPrice) {
        return new ProductExhibition(id, name, description, category, newPrice, promotionalPrice);
    }
}
