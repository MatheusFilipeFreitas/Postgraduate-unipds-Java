package com.mathffreitas.basics.model.with_discount;

import com.mathffreitas.basics.model.Product;

public class ProductWithDiscount extends Product {
    public double discount;

    public ProductWithDiscount(long id, String name, double price, double discount) {
        super(id, name, price);
        this.discount = discount;
    }
}
