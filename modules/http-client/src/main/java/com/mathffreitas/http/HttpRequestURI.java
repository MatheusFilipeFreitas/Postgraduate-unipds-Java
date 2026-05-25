package com.mathffreitas.http;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import java.net.http.HttpRequest;
public class HttpRequestURI {
    private HttpProtocol protocol;
    private String host;
    private List<String> path;
    private int port = -1;

    public HttpRequestURI(HttpProtocol protocol, String host, int port, List<String> path) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
    }

    public HttpRequestURI(HttpProtocol protocol, String host, List<String> path) {
        this.protocol = protocol;
        this.host = host;
        this.path = path;
    }

    public URL toURL() throws MalformedURLException {
        String fullPath = getFullPathFormatted();

        if (port != -1) {
            return new URL(protocol.toString(), host, port, fullPath);
        } else {
            return new URL(protocol.toString(), host, fullPath);
        }
    }

    public URI toURI() throws URISyntaxException {
        String fullPath = getFullPathFormatted();

        if (port != -1) {
            return new URI(protocol.toString(), null, host, port, fullPath, null, null);
        } else {
            return new URI(protocol.toString(), host, fullPath, null);
        }
    }

    private String getFullPathFormatted() {
        return "/" + path.stream()
                .map(p -> p.replaceAll("^/|/$", ""))
                .reduce((a, b) -> a + "/" + b)
                .orElse("");
    }
}
