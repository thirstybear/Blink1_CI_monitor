package uk.co.thirstybear.blink1service.blink1.actions;

import java.io.InputStream;

class CommandLineRunner {

    private final String command;

    CommandLineRunner(String command) {
        this.command = command;
    }

    void run() {
        try {
            final Process process = Runtime.getRuntime().exec(command);
            consumeStream(process.getInputStream());
            consumeStream(process.getErrorStream());
            process.waitFor();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void consumeStream(InputStream stream) {
        StreamSlurper slurper = new StreamSlurper(stream, new RedirectToStdOut());
        slurper.start();
    }
}
