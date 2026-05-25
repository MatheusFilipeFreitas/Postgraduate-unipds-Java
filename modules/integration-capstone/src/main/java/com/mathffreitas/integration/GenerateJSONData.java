package com.mathffreitas.integration;

import com.google.gson.Gson;
import com.mathffreitas.integration.models.ProductExhibition;
import com.mathffreitas.integration.repositories.Database;
import com.mathffreitas.integration.repositories.utils.DatabaseSettings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GenerateJSONData {

    public static void main(String[] args) throws IOException {
        DatabaseSettings settings = new DatabaseSettings(false, true);
        Database database = new Database(settings);
        database.initDatabase();

        List<ProductExhibition> products = database.getProducts();

        Gson  gson = new Gson();
        String json = gson.toJson(products);
        Path path = Path.of("target", "output.json");
        path.getParent().toFile().mkdirs();
        Files.write(path, json.getBytes());
    }
}
