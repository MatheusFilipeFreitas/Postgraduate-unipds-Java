package com.mathffreitas.integration;

import com.mathffreitas.integration.models.ProductExhibition;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.IO;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpClientMain {
    public static void main(String[] args) throws Exception {
        URI uri = URI.create("http://localhost:8000/output");

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder(uri)
                    .header("Accept", "application/x-java-serialized-object")
                    .build();
            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

            int statusCode = response.statusCode();
            byte[] body = response.body();

            var bis = new ByteArrayInputStream(body);
            var ois = new ObjectInputStream(bis);

            IO.println(statusCode);
            List<ProductExhibition> products = (List<ProductExhibition>) ois.readObject();
            products.forEach(IO::println);
        }
    }
}
