package com.mathffreitas.integration;

import java.lang.IO;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientMain {
    public static void main(String[] args) throws Exception {
        URI uri = URI.create("http://localhost:8000/output.json");

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder(uri).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String body = response.body();
            IO.println("[" + statusCode + "] - " + body);
        }
    }
}
