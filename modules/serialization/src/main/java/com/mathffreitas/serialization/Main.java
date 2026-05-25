package com.mathffreitas.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Pix pix = new Pix(1, new BigDecimal("10.99"), "matheus.freitas");
        writeInFile(pix);
        readInFile();
    }

    static void writeInFile(Pix pix) throws IOException {
        Path output = Path.of("target", "pix.ser");
        output.getParent().toFile().mkdirs();
        try (
                var fos = new FileOutputStream(output.toString());
                var oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(pix);
        }
    }

    static void readInFile() throws IOException, ClassNotFoundException {
        Path input = Path.of("target", "pix.ser");
        try (
                var fis = new FileInputStream(input.toString());
                var ois = new ObjectInputStream(fis)
        ) {
            Pix pix = (Pix) ois.readObject();
            System.out.println("Pix read: " + pix);
        }
    }
}
