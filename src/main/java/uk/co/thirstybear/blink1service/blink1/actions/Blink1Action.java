package uk.co.thirstybear.blink1service.blink1.actions;

public enum Blink1Action {
    SOLIDRED("blink1-tool --red"),
    SOLIDGREEN("blink1-tool --green"),
    FLASHRED("blink1-tool -t 200 -m 100 --red --blink 5"),
    FLASHGREEN("blink1-tool -t 200 -m 100 --green --blink 5");

    private CommandLineRunner command;

    Blink1Action(String command) {
        this.command = new CommandLineRunner(command);
    }

    public void apply() {
        command.run();
    }

}
