package uk.co.thirstybear.blink1service.blink1.actions;

public enum Blink1Action {
    SOLIDRED("blink1-tool --red"),
    SOLIDGREEN("blink1-tool --green"),
    FLASHRED("blink1-tool -t 200 -m 100 --red --blink 5"),
    FLASHGREEN("blink1-tool -t 200 -m 100 --green --blink 5");

    private String command;

    private Blink1Action(String command) {
        this.command = command;
    }

    // TODO IMPORTANT - need to consume the outputs std/err!!
    // TODO break the invoker out to generic command line runner object
    public void invoke() {
        try {
            final Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
