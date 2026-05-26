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
            InputStream in = clientSocket.getInputStream();
            StringBuilder stringBuilder = new StringBuilder();

            int data;
            do {
                data = in.read();
                stringBuilder.append((char) data);
            } while (in.available() > 0);

            String request = stringBuilder.toString();
            logger.finest(() -> request);

            Thread.sleep(250);

            String[] requestChunks = request.split("\r\n\r\n");
            String requestLineAndHeaders = requestChunks[0];
            String[] requestLineAndHeadersChunks = requestLineAndHeaders.split("\r\n");
            String requestLine = requestLineAndHeadersChunks[0];
            String[] requestLineChunks = requestLine.split(" ");

            String method = requestLineChunks[0];
            String uri = requestLineChunks[1];

            logger.finer(() -> "Method: " + method);
            logger.finer(() ->"Path: " + uri);

            OutputStream out = clientSocket.getOutputStream();
            PrintStream printStream = new PrintStream(out);

            if (method.equals("GET") && uri.equals("/output")) {
//                Path path = Path.of("output.json");
//                String json = Files.readString(path);
                String json = new Gson().toJson(database.getProducts());

                displayOkResponse(printStream, json);
            } else

            if (method.equals("GET") && uri.equals("/output/total")) {
                int total = database.getSizeOfProducts();
                displayOkResponse(printStream, ("" + total));
            } else

            if (method.equals("POST") && uri.equals("/output")) {
                if (requestChunks.length == 1) {
                    printStream.println("HTTP/1.1 400 Bad Request");
                    return;
                }

                String body =  requestChunks[1];
                ProductExhibition product = new Gson().fromJson(body, ProductExhibition.class);
                database.addProduct(product);

                printStream.println("HTTP/1.1 201 Created");
            } else

            if (method.equals("GET") && uri.matches("/output/\\d+")) {
                Long id = getIdFromUri(uri);
                Optional<ProductExhibition> product = database.getProductById(id);
                if (product.isEmpty()) {
                    printStream.println("HTTP/1.1 404 Not Found");
                } else {
                    String json = new Gson().toJson(product.get());
                    displayOkResponse(printStream, json);
                }
            }

            if (method.equals("PATCH") && uri.matches("/output/\\d+")) {
                Long id = getIdFromUri(uri);
                Optional<ProductExhibition> product = database.getProductById(id);
                if (product.isEmpty()) {
                    printStream.println("HTTP/1.1 404 Not Found");
                } else {
                    String body =  requestChunks[1];
                    BigDecimal price = new BigDecimal(new Gson().fromJson(body, String.class));
                    database.updateProductPriceById(id, price);
                    displayOkResponse(printStream, "");
                }
            }

            else {
                printStream.println("HTTP/1.1 404 Not Found");
            }
        }
    }

    private static Long getIdFromUri(String uri) {
        String idText = uri.substring("/output/".length());
        return Long.parseLong(idText);
    }

    private static void displayOkResponse(PrintStream printStream, String response) {
        printStream.println("HTTP/1.1 200 OK");
        printStream.println("Content-Type: application/json; charset=utf-8");
        printStream.println();
        printStream.println(response);
    }
}
