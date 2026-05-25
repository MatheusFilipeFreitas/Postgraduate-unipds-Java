package com.mathffreitas.patterns.utils.builders.implementations;

import com.mathffreitas.patterns.models.Product;
import com.mathffreitas.patterns.utils.builders.BaseBuilder;

public class ProductBuilder extends BaseBuilder<Product> {

    public ProductBuilder setId(String id) {
        try
        {
            this.instance.id = Long.parseLong(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return this;
    }

    public ProductBuilder setName(String name) {
        try
        {
            this.instance.name = name;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void validate() {

    }

    @Override
    public Product build() {
        return null;
    }
}
