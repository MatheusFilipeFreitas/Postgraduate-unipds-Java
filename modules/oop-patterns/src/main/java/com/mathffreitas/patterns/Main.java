package com.mathffreitas.patterns;

import com.mathffreitas.patterns.models.Menu;
import com.mathffreitas.patterns.models.Product;
import com.mathffreitas.patterns.models.types.FileType;
import com.mathffreitas.patterns.utils.IReader;
import com.mathffreitas.patterns.utils.factory.ReaderFactory;
import com.mathffreitas.patterns.utils.mapper.implementations.ProductMapper;

import java.lang.IO;
import java.util.List;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
    ReaderFactory<Product> factory = new ReaderFactory<>();
    IReader<Product> reader = factory.create(FileType.CSV);
    List<Product> products = reader.read(new ProductMapper());
    Menu menu = new Menu(products);
    IO.print(menu.toString());
}
}
