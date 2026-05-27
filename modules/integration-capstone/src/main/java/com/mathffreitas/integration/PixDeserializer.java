package com.mathffreitas.integration;

import com.mathffreitas.integration.models.Pix;

import java.io.*;

public class PixDeserializer {
    static void main() throws IOException {
        try (
                FileInputStream fis = new FileInputStream("pix.ser");
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            Pix pix = (Pix) ois.readObject();
            IO.println(pix);
            IO.println(pix.getDestinyKey());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
