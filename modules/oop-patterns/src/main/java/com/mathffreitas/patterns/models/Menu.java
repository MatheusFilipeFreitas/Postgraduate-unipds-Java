package com.mathffreitas.patterns.models;

import java.util.List;

public class Menu {
    public List<Product> products;

    public Menu(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("===== MENU =====\n");

        for (Product product : products) {
            sb.append(product).append("\n");
        }

        return sb.toString();
    }
}
