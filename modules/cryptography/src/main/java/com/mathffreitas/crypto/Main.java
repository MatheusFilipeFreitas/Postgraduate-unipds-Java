package com.mathffreitas.crypto;

import java.lang.IO;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main {
    public static void main(String[] args) throws Exception {
    String text = "Hello, World!";

    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);
    SecretKey secretKey = keyGenerator.generateKey();

    String cryptographedText = Crypto.encrypt(text, secretKey);
    IO.println(cryptographedText);

    String decryptedText = Crypto.decrypt(cryptographedText, secretKey);
    IO.println(decryptedText);
}
}
