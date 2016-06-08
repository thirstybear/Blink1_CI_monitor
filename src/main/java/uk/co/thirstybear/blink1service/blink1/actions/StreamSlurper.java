package uk.co.thirstybear.blink1service.blink1.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class StreamSlurper extends Thread {
    private final InputStream stream;

    StreamSlurper(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public void run() {
        try(InputStreamReader inputStreamreader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamreader)) {
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
