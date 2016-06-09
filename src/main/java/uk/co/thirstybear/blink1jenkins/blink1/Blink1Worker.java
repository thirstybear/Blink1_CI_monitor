package uk.co.thirstybear.blink1jenkins.blink1;

import static uk.co.thirstybear.blink1jenkins.blink1.actions.Blink1Action.*;

public class Blink1Worker {

    public void buildFailed() {
        FLASHRED.apply();
        SOLIDRED.apply();
    }

    public void buildPassed() {
        FLASHGREEN.apply();
        SOLIDGREEN.apply();
    }

    public void oops() {
        FLASHRED_CONTINUOUS.apply();
    }
}