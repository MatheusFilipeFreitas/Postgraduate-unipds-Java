package com.mathffreitas.patterns.models;

import com.mathffreitas.patterns.models.types.ProductCategoryType;

public class Product {
    public long id;
    public String name;
    public String description;
    public double price;
    public ProductCategoryType category;
    public boolean discountActive;
    public double priceWithDiscount;
    public boolean noTaxes;


    public Product(
            long id,
            String name,
            String description,
            double price,
            ProductCategoryType category,
            boolean discountActive,
            double priceWithDiscount,
            boolean noTaxes
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.discountActive = discountActive;
        this.priceWithDiscount = priceWithDiscount;
        this.noTaxes = noTaxes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Product { ")
                .append("id=").append(id)
                .append(", name='").append(name).append('\'')
                .append(", description='").append(description).append('\'')
                .append(", category=").append(category)
                .append(", price=").append(String.format("%.2f", price));

        if (discountActive) {
            sb.append(", discountPrice=")
                    .append(String.format("%.2f", priceWithDiscount));
        }

        sb.append(", noTaxes=").append(noTaxes)
                .append(" }");

        return sb.toString();
    }

}
