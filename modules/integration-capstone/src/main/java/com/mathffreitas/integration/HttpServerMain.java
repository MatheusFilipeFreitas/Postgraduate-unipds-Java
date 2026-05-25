package com.mathffreitas.integration;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.stream.Stream;
import java.net.Socket;
public class HttpServerMain {


    public static void main(String[] args) throws Exception {

        InetSocketAddress address = new InetSocketAddress(8080);
        HttpServer server = HttpServer.create(address, 0);

        server.createContext("/output", exchange -> {

            Path path = Path.of("target", "output.json");

            System.out.println(path.toAbsolutePath());

            String json = Files.readString(path);

            System.out.println(json);

            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);

            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "application/json; charset=utf-8");

            exchange.sendResponseHeaders(200, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        });

        System.out.println("Http server up!");

        server.start();
    }
}