package com.mathffreitas.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.stream.Stream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
public class OldHttpClient {
    public InputStream getRequest(HttpRequestURI requestURI) throws IOException {
        URL url = generateURL(requestURI);
        return getInputStream(url);
    }

    private URL generateURL(HttpRequestURI requestURI) throws MalformedURLException {
        return requestURI.toURL();
    }

    private InputStream getInputStream(URL url) throws IOException {
        return url.openStream();
    }
}
