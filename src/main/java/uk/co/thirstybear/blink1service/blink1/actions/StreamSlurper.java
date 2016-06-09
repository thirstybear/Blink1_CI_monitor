package uk.co.thirstybear.blink1service.blink1.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class StreamSlurper extends Thread {
    private final InputStream stream;
    private final List<StreamListener> listeners = new ArrayList<>();

    StreamSlurper(InputStream stream) {
        this.stream = stream;
    }

    StreamSlurper(InputStream stream, StreamListener listener) {
        this(stream);
        this.listeners.add(listener);
    }

    @Override
    public void run() {
        try(InputStreamReader inputStreamreader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamreader)) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                for(StreamListener listener: listeners) {
                    listener.notify(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
