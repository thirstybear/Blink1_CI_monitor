package uk.co.thirstybear.blink1service.blink1.actions;

public class CommandLineRunner {

    private final String command;

    public CommandLineRunner(String command) {
        this.command = command;
    }

    // TODO IMPORTANT - need to consume the outputs std/err!!
    public void run() {
        try {
            final Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
