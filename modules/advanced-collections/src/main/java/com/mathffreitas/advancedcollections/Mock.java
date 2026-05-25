package com.mathffreitas.advancedcollections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Mock {
    public List<ProductExhibition> mockProductsData() {
        List<ProductExhibition> mockedData = new ArrayList<>();

        ProductExhibition limonadaExibition = new ProductExhibition(
                1L,
                "Limonada",
                "Bebida feita com limão",
                Category.BEBIDAS,
                new BigDecimal("2.99"),
                null
        );

        mockedData.add(limonadaExibition);

        ProductExhibition cafeExibition = new ProductExhibition(
                2L,
                "Cafe",
                "Bebida feita com cafe",
                Category.BEBIDAS,
                new BigDecimal("1.89"),
                null
        );

        mockedData.add(cafeExibition);

        ProductExhibition pudimExibition = new ProductExhibition(
                3L,
                "Pudim",
                "Sobremesa com caramelo",
                Category.SOBREMESA,
                new BigDecimal("4.00"),
                null
        );

        mockedData.add(pudimExibition);

        ProductExhibition risottoExibition = new ProductExhibition(
                4L,
                "Risotto",
                "Arroz e frango com molho",
                Category.PRATOS_PRINCIPAIS,
                new BigDecimal("16.89"),
                null
        );

        mockedData.add(risottoExibition);

        return mockedData;
    }
}
