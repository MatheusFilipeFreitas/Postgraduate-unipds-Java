package com.mathffreitas.integration;

import com.google.gson.Gson;
import com.mathffreitas.integration.models.ProductExhibition;
import com.mathffreitas.integration.repositories.InMemoryDatabase;
import com.mathffreitas.integration.repositories.SqlDatabase;
import com.mathffreitas.integration.repositories.utils.DatabaseSettings;

import java.io.*;
import java.lang.IO;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerWithSocket {
    private static final Logger logger = Logger.getLogger(ServerWithSocket.class.getName());
    private static final SqlDatabase database;

    static {
        try {
            database = new SqlDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void main(String[] args) throws Exception {
        try (ExecutorService executor = Executors.newFixedThreadPool(50)) {
            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                logger.info(() -> "Welcome to the server!");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    executor.execute(() -> {
                        try {
                            handleRequests(clientSocket);
                        } catch (Exception e) {
//                            logger.severe(() -> e.getMessage());
                            logger.log(Level.SEVERE, e.getMessage(), e);
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        }
    }

    private static void handleRequests(Socket clientSocket) throws IOException, InterruptedException {
        try (clientSocket) {
            String request = readRequest(clientSocket);
            logger.finest(() -> request);

            Thread.sleep(250);

            String[] requestChunks = request.split("\r\n\r\n");
            String[] requestLineChunks = requestChunks[0]
                    .split("\r\n")[0]
                    .split(" ");

            String method = requestLineChunks[0];
            String uri = requestLineChunks[1];
            String httpVersion = requestLineChunks[2];

            logger.finer(() -> "Method: " + method);
            logger.finer(() -> "Path: " + uri);
            logger.finer(() -> "HTTP Version: " + httpVersion);

            PrintStream printStream = new PrintStream(clientSocket.getOutputStream());

            routeRequest(method, uri, requestChunks, printStream);
        }
    }

    private static String readRequest(Socket clientSocket) throws IOException {
        InputStream in = clientSocket.getInputStream();
        StringBuilder builder = new StringBuilder();

        int data;
        do {
            data = in.read();
            builder.append((char) data);
        } while (in.available() > 0);

        return builder.toString();
    }

    private static String handleMediaType(String[] requestChunks) {
        String[] headers = requestChunks[0].split("\r\n");

        for (int i = 1; i < headers.length; i++) {
            String header = headers[i];

            logger.info("Header: " + header);

            if (header.toLowerCase().startsWith("accept:")) {
                String mediaType = header.substring("Accept:".length()).trim();

                logger.info("Accept: " + mediaType);

                return mediaType;
            }
        }

        return "application/json";
    }

    private static void routeRequest(
            String method,
            String uri,
            String[] requestChunks,
            PrintStream printStream
    ) throws IOException {
        String mediaType = handleMediaType(requestChunks);
        if (method.equals("GET") && uri.equals("/output")) {
            handleGetProducts(printStream, mediaType);
            return;
        }

        if (method.equals("GET") && uri.equals("/output/total")) {
            handleGetTotal(printStream);
            return;
        }

        if (method.equals("POST") && uri.equals("/output")) {
            handleCreateProduct(requestChunks, printStream);
            return;
        }

        if (method.equals("GET") && uri.matches("/output/\\d+")) {
            handleGetProductById(uri, printStream);
            return;
        }

        if (method.equals("PATCH") && uri.matches("/output/\\d+")) {
            handlePatchProduct(uri, requestChunks, printStream);
            return;
        }

        printStream.print("HTTP/1.1 404 Not Found\r\n");
    }

    private static void handleGetProducts(PrintStream printStream, String mediaType) throws IOException {
        byte[] body;
        List<ProductExhibition> products = database.getProducts();
        if ("application/x-java-serialized-object".equals(mediaType)) {
            var bos = new ByteArrayOutputStream();
            var oos = new ObjectOutputStream(bos);
            oos.writeObject(products);
            body = bos.toByteArray();
        } else {
            String json = new Gson().toJson(products.toArray());
            body = json.getBytes(StandardCharsets.UTF_8);
        }

        displayOkResponse(printStream, body, mediaType);
    }

    private static void handleGetTotal(PrintStream printStream) throws IOException {
        int total = database.getSizeOfProducts();
        displayOkResponse(printStream, String.valueOf(total));
    }

    private static void handleCreateProduct(
            String[] requestChunks,
            PrintStream printStream
    ) {
        if (requestChunks.length == 1) {
            printStream.print("HTTP/1.1 400 Bad Request\r\n");
            return;
        }

        String body = requestChunks[1];

        ProductExhibition product =
                new Gson().fromJson(body, ProductExhibition.class);

        database.addProduct(product);

        printStream.print("HTTP/1.1 201 Created\r\n");
    }

    private static void handleGetProductById(
            String uri,
            PrintStream printStream
    ) throws IOException {
        Long id = getIdFromUri(uri);

        Optional<ProductExhibition> product =
                database.getProductById(id);

        if (product.isEmpty()) {
            printStream.print("HTTP/1.1 404 Not Found\r\n");
            return;
        }

        String json = new Gson().toJson(product.get());
        displayOkResponse(printStream, json);
    }

    private static void handlePatchProduct(
            String uri,
            String[] requestChunks,
            PrintStream printStream
    ) throws IOException {
        Long id = getIdFromUri(uri);

        Optional<ProductExhibition> product =
                database.getProductById(id);

        if (product.isEmpty()) {
            printStream.print("HTTP/1.1 404 Not Found\r\n");
            return;
        }

        String body = requestChunks[1];

        BigDecimal price =
                new BigDecimal(new Gson().fromJson(body, String.class));

        database.updateProductPriceById(id, price);

        displayOkResponse(printStream, "");
    }

    private static Long getIdFromUri(String uri) {
        String idText = uri.substring("/output/".length());
        return Long.parseLong(idText);
    }

    private static void displayOkResponse(PrintStream printStream, String response) throws IOException {
        printStream.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
        printStream.write("Content-Type: application/json; charset=utf-8\r\n\r\n".getBytes(StandardCharsets.UTF_8));
        printStream.write(response.getBytes(StandardCharsets.UTF_8));
        printStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
    }

    private static void displayOkResponse(PrintStream printStream, byte[] response, String mediaType) throws IOException {
        printStream.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
        printStream.write(("Content-Type: " + mediaType + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));
        printStream.write(response);
        printStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
    }
}
