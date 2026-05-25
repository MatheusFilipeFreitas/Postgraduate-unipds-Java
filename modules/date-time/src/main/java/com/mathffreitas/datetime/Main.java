package com.mathffreitas.datetime;

import java.lang.IO;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Main {
    public static void main(String[] args) {
    LocalDate actualDate = LocalDate.now();
    LocalTime actualTime = LocalTime.now();
    LocalDateTime actualDateTime = LocalDateTime.now();

    IO.println(actualDate);
    IO.println(actualTime);
    IO.println(actualDateTime);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    IO.println(actualDate.format(formatter));

    String userDate = "01/05/2025";
    LocalDate date = LocalDate.parse(userDate, formatter);

    IO.println(date);
}
}
