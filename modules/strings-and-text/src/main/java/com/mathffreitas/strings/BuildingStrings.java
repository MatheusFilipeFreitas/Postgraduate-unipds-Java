package com.mathffreitas.strings;

import java.lang.IO;
public class BuildingStrings {
    public static void main(String[] args) {
    long start = System.currentTimeMillis();
    String test = "";
    for (long i = 1; i <= 1_000L; i++) {
        test += i + ", ";
    }
    long end = System.currentTimeMillis();
    IO.println(end - start);
    IO.println(test);

    long newStart = System.currentTimeMillis();
    StringBuilder builder = new StringBuilder();
    for (long i = 1; i <= 1_000L; i++) {
        builder.append(i).append(", ");
    }
    long newEnd = System.currentTimeMillis();
    IO.println(newEnd - newStart);
    IO.println(builder);

    // Também existe o StringBuffer, que é thread-safe, mas isso custa performance, sendo mais lento que
    // o StringBuilder
}
}
