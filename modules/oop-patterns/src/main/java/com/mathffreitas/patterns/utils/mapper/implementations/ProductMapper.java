package com.mathffreitas.patterns.utils.mapper.implementations;

import com.mathffreitas.patterns.models.Product;
import com.mathffreitas.patterns.models.types.ProductCategoryType;
import com.mathffreitas.patterns.utils.mapper.IMapper;

public class ProductMapper implements IMapper<Product> {
    @Override
    public Product parse(String[] values) {
        return new Product(
                Long.parseLong(values[0]),
                values[1],
                values[2],
                Double.parseDouble(values[3]),
                ProductCategoryType.valueOf(values[4]),
                Boolean.parseBoolean(values[5]),
                (Boolean.parseBoolean(values[5]))
                    ? Double.parseDouble(values[6])
                    : 0,
                Boolean.parseBoolean(values[7])
        );
    }
}
