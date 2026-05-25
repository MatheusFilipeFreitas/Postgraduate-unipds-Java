package com.mathffreitas.strings;

import java.lang.IO;
public class Main {
    public static void main(String[] args) {
    String itemName1 = "Refresco do Chaves";
    IO.println(itemName1.toUpperCase());
    IO.println(itemName1.toLowerCase());
    IO.println(itemName1.length());
    IO.println(itemName1.charAt(0));
    IO.println(itemName1.charAt(1));
    IO.println(itemName1.toLowerCase().replace(" ", "-"));
    IO.println(itemName1.contains("Chaves"));
    IO.println(itemName1.contains("Kiko"));
    IO.println(itemName1.contains("chaves"));
    IO.println(itemName1.startsWith("Chaves"));
    IO.println(itemName1.endsWith("Chaves"));
    IO.println(itemName1.concat(" teste"));
    IO.println(itemName1.substring(0, 8));

    String[] pieces = itemName1.split(" ");
    IO.println(pieces.length);

    for (String piece : pieces) {
        IO.println(piece);
    }

    String entry = IO.readln("Value: ");
    IO.println(entry);
    IO.println(entry == itemName1);
    IO.println(entry.equals(itemName1)); // case-sensitive
    IO.println(entry.equalsIgnoreCase(itemName1)); // case-insensitive
}
}
