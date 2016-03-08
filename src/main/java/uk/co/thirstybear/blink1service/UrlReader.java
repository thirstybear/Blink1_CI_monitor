package uk.co.thirstybear.blink1service;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UrlReader {
    private final String url;

    public UrlReader(String url) {
        if (!url.startsWith("http://")) {
            url = "http://" + url;
        }
        this.url = url;
    }

    public String get() throws IOException {
        InputStream in = new URL(url).openStream();

        String data;
        try {
            data = IOUtils.toString(in);
        } finally {
            IOUtils.closeQuietly(in);
        }
        return data;
    }
}
