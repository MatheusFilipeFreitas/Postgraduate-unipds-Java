package com.mathffreitas.http;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.lang.IO;
import java.util.List;
import java.util.stream.Stream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URI;
public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
    HttpRequestURI requestURI = getHttpRequestURI();
//    oldVersion(requestURI);
    newVersion(requestURI);
}

public static HttpRequestURI getHttpRequestURI() {
    String host = "viacep.com.br";
    List<String> path = List.of(
            "ws",
            "01001000",
            "json"
    );

    return new HttpRequestURI(
            HttpProtocol.https,
            host,
            path
    );
}

public static void oldVersion(HttpRequestURI requestURI) throws IOException {
    OldHttpClient httpClient = new OldHttpClient();

    try (InputStream stream = httpClient.getRequest(requestURI)) {
        String response = new String(stream.readAllBytes());
        IO.println(response);
    }
}

public static void newVersion(HttpRequestURI requestURI) throws URISyntaxException, IOException, InterruptedException {
    URI uri = requestURI.toURI();

    try (HttpClient httpClient = HttpClient.newHttpClient()) {
        HttpRequest request = HttpRequest.newBuilder(uri).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        IO.println(response.statusCode());
        IO.println(response.body());
    }
}
}
