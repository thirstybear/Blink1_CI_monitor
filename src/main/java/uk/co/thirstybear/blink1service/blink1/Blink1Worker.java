package uk.co.thirstybear.blink1service.blink1;

import static uk.co.thirstybear.blink1service.blink1.actions.Blink1Action.*;

public class Blink1Worker {

    public void buildFailed() {
        FLASHRED.invoke();
        SOLIDRED.invoke();
    }

    public void buildPassed() {
        FLASHGREEN.invoke();
        SOLIDGREEN.invoke();
    }
}
