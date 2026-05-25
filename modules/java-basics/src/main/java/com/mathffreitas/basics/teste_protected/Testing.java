package com.mathffreitas.basics.teste_protected;

import com.mathffreitas.basics.model.Product;
import com.mathffreitas.basics.model.with_discount.ProductWithDiscount;

public class Testing {
    void main () {
        ProductWithDiscount withDiscount = new ProductWithDiscount(
            1,
            "Testing",
            10.14,
            0.10
        );

//        Product product = new Product(
//                1,
//                "Testing",
//                10.14
//        );
    }
}
