package com.mathffreitas.regex;

import java.lang.IO;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Main {
    public static void main(String[] args) {
    Pattern emailPattern = Pattern.compile("^[\\w.-_]+@[\\w]+\\.[a-zA-Z0-9]{2,6}$");
    String email = "test@test.com";
    IO.println("Email: " + email);
    IO.println(email.matches(emailPattern.pattern()));

    String otherEmail = "invalid-email";
    IO.println("Other Email: " + otherEmail);
    IO.println(otherEmail.matches(emailPattern.pattern()));

    //with matcher
    Matcher matcher = emailPattern.matcher(email);
    if (matcher.find()) {
        IO.println(matcher.group());
    }

    String text = "This zip code is 01234-567";
    Pattern zipCodePattern = Pattern.compile("\\d{5}-\\d{3}");
    Matcher zipCodeMatcher = zipCodePattern.matcher(text);
    if (zipCodeMatcher.find()) {
        IO.println("Zip code found: " + zipCodeMatcher.group());
    }
}
}
