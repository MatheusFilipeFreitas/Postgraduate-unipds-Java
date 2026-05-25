package com.mathffreitas.advancedcollections;

import java.math.BigDecimal;

public record ProductExhibition(
        Long id,
        String name,
        String description,
        Category category,
        BigDecimal price,
        BigDecimal promotionalPrice
    ) {

    public ProductExhibition setNewPrice(BigDecimal newPrice) {
        return new ProductExhibition(id, name, description, category, newPrice, promotionalPrice);
    }
}
