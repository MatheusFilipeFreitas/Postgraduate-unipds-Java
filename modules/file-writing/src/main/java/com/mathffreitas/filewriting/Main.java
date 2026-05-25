package com.mathffreitas.filewriting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
    Path output = Path.of("target", "benchmark.txt");
    output.getParent().toFile().mkdirs();
    String filename = output.toString();
    String line = "Running benchmark...";
    long size = 200L * 1024 * 1024;

    try {
        long actualSize = 0;
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        while (actualSize < size) {
            writer.write(line);
            actualSize += line.getBytes().length;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
