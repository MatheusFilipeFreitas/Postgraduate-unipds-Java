package com.mathffreitas.integration.repositories;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import java.util.stream.Stream;
public class ClientWithSocket {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new  Socket("localhost", 8000)) {
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);

            ps.println("GET /output.json HTTP/1.1");
            ps.println();

            InputStream is = socket.getInputStream();
            Scanner in = new Scanner(is);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }
    }
}
