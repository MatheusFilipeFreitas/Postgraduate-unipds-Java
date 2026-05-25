package com.mathffreitas.integration;

import java.lang.IO;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.stream.Stream;
public class ServerWithSocket {
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            IO.println("Welcome to the server!");

            while (true) {
                try(Socket clientSocket = serverSocket.accept()) {
                    InputStream in = clientSocket.getInputStream();
                    StringBuilder stringBuilder = new StringBuilder();

                    int data;
                    do {
                        data = in.read();
                        stringBuilder.append((char) data);
                    } while (in.available() > 0);

                    String request = stringBuilder.toString();
                    IO.println(request);

                    Path path = Path.of("output.json");
                    String json = Files.readString(path);

                    OutputStream out = clientSocket.getOutputStream();
                    PrintStream printStream = new PrintStream(out);

                    printStream.println("HTTP/1.1 200 OK");
                    printStream.println("Content-Type: application/json; charset=utf-8");
                    printStream.println();
                    printStream.println(json);
                }
            }
        }
    }
}
