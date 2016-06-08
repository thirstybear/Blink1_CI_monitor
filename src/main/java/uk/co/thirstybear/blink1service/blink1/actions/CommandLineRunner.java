package uk.co.thirstybear.blink1service.blink1.actions;

public class CommandLineRunner {

    private final String command;

    CommandLineRunner(String command) {
        this.command = command;
    }

    // TODO IMPORTANT - need to consume the outputs std/err!!
    void run() {
        try {
            final Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
