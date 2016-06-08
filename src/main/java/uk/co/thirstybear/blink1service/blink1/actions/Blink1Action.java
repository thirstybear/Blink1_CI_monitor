package uk.co.thirstybear.blink1service.blink1.actions;

public class Blink1Action {
    public final static Blink1Action SOLIDRED = new Blink1Action("blink1-tool --red");
    public final static Blink1Action SOLIDGREEN = new Blink1Action("blink1-tool --green");

    public final static Blink1Action FLASHRED = new Blink1Action("blink1-tool -t 200 -m 100 --red --blink 5");
    public final static Blink1Action FLASHGREEN = new Blink1Action("blink1-tool -t 200 -m 100 --green --blink 5");

    private String command;

    private Blink1Action(String command) {
        this.command = command;
    }

    // TODO IMPORTANT - need to consume the outputs std/err!!
    public void invoke() {
        try {
            final Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
