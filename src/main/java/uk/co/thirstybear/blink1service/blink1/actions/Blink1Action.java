package uk.co.thirstybear.blink1service.blink1.actions;

public abstract class Blink1Action {
    private String command;

    Blink1Action(String command) {
        this.command = command;
    }

    public void invoke() {
        try {
            final Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
