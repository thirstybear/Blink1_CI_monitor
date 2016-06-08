package uk.co.thirstybear.blink1service.blink1.actions;

public class FlashRedAction extends Blink1Action {
    public FlashRedAction() {
        super("blink1-tool -t 200 -m 100 --red --blink 5");
    }
}
