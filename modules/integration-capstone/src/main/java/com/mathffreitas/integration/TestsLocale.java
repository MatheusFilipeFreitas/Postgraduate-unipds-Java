package com.mathffreitas.integration;

import java.text.NumberFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestsLocale {
    static void main() {
        Locale.availableLocales().forEach((locale) -> {IO.println("Locale: " + locale);});
        IO.println("Default locale: " + Locale.getDefault());

        Locale usLocale = Locale.US;
        Locale createLocale = Locale.of("pt", "BR");
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        IO.println(formatter.format(ZonedDateTime.now()));
        IO.println(formatter.withLocale(usLocale).format(ZonedDateTime.now()));
        IO.println(formatter.withLocale(createLocale).format(ZonedDateTime.now()));

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMMM/yyyy");
        IO.println(formatter2.format(ZonedDateTime.now()));

        IO.println(NumberFormat.getCurrencyInstance().format(2.99));
        IO.println(NumberFormat.getCurrencyInstance(usLocale).format(2.99));
        IO.println(NumberFormat.getCurrencyInstance(createLocale).format(2.99));

        ResourceBundle resourceBundleUS = ResourceBundle.getBundle("messages", Locale.US);
        ResourceBundle resourceBundleBR = ResourceBundle.getBundle("messages", createLocale);

        IO.println(resourceBundleUS.getString("category.appetizers"));
        IO.println(resourceBundleBR.getString("category.appetizers"));
    }
}
