package uk.co.thirstybear.blink1jenkins.jenkins;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class UrlReader {
    private final String url;

    UrlReader(String url) {
        if (!url.startsWith("http://")) {
            url = "http://" + url;
        }
        this.url = url;
    }

    String get() throws IOException {
        String data;
        try (InputStream in = new URL(url).openStream()){
            data = IOUtils.toString(in);
        }
        return data;
    }
}
