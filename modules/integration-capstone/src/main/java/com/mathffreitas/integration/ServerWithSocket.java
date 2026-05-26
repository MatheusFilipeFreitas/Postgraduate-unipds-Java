package com.mathffreitas.integration;

import com.google.gson.Gson;
import com.mathffreitas.integration.models.ProductExhibition;
import com.mathffreitas.integration.repositories.InMemoryDatabase;
import com.mathffreitas.integration.repositories.SqlDatabase;
import com.mathffreitas.integration.repositories.utils.DatabaseSettings;

import java.io.IOException;
import java.lang.IO;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

import java.sql.SQLException;
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

            logger.finer(() -> "Method: " + method);
            logger.finer(() -> "Path: " + uri);

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

    private static void routeRequest(
            String method,
            String uri,
            String[] requestChunks,
            PrintStream printStream
    ) {
        if (method.equals("GET") && uri.equals("/output")) {
            handleGetProducts(printStream);
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

    private static void handleGetProducts(PrintStream printStream) {
        String json = new Gson().toJson(database.getProducts());
        displayOkResponse(printStream, json);
    }

    private static void handleGetTotal(PrintStream printStream) {
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
    ) {
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
    ) {
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

    private static void displayOkResponse(PrintStream printStream, String response) {
        printStream.print("HTTP/1.1 200 OK\r\n");
        printStream.print("Content-Type: application/json; charset=utf-8\r\n\r\n");
        printStream.print(response);
        printStream.println("\r\n");
    }
}
