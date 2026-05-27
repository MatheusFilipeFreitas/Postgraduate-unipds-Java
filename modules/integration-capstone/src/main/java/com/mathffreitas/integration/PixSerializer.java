package com.mathffreitas.integration;

import com.mathffreitas.integration.models.Pix;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

public class PixSerializer {
    static void main() throws IOException, ClassNotFoundException {
        var pix = new Pix(1L, new BigDecimal("10.00"), "destiny-key");

        try (
                FileOutputStream file = new FileOutputStream("pix.ser");
                ObjectOutputStream oos = new ObjectOutputStream(file)
        ) {
            oos.writeObject(pix);
        }

    }
}
