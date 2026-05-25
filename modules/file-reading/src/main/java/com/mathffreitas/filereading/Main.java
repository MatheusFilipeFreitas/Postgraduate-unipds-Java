package com.mathffreitas.filereading;

import java.lang.IO;
import java.util.List;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
public class Main {

    public static void main(String[] args) throws IOException {
    Path path = Path.of("target", "benchmark.txt");
    if (!Files.exists(path)) {
        path = Paths.get("..", "file-writing", "target", "benchmark.txt");
    }

    //java io
    long initTime, endTime;
    initTime = System.currentTimeMillis();
    try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
        String line;
        while ((line = reader.readLine()) != null) {
            // Process the line
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    endTime = System.currentTimeMillis();
    IO.println("javaio - Time taken: " + (endTime - initTime) + " ms");

    //java nio
    initTime = System.currentTimeMillis();
    FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);
    ByteBuffer buffer = ByteBuffer.allocate(8192); // 8KB buffer
    while (fileChannel.read(buffer) > 0) {
        buffer.flip();
        buffer.clear();
    }
    fileChannel.close();
    endTime = System.currentTimeMillis();

    IO.println("javaio - Time taken: " + (endTime - initTime) + " ms");

    //read all lines
    initTime = System.currentTimeMillis();
    List<String> lines = Files.readAllLines(path);
    endTime = System.currentTimeMillis();
    IO.println("readAllLines - Time taken: " + (endTime - initTime) + " ms");
}
}
