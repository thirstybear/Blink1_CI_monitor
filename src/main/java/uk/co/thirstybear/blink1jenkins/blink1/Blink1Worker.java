package uk.co.thirstybear.blink1jenkins.blink1;

import uk.co.thirstybear.blink1jenkins.jenkins.JenkinsState;

import static uk.co.thirstybear.blink1jenkins.blink1.actions.Blink1Action.*;
import static uk.co.thirstybear.blink1jenkins.jenkins.JenkinsState.FAIL;
import static uk.co.thirstybear.blink1jenkins.jenkins.JenkinsState.PASS;

public class Blink1Worker {
    private JenkinsState currentState;

    public void buildFailed() {
        if (currentState == FAIL) return;

        currentState = FAIL;
        FLASHRED.apply();
        SOLIDRED.apply();
    }

    public void buildPassed() {
        if (currentState == PASS) return;

        currentState = PASS;
        FLASHGREEN.apply();
        SOLIDGREEN.apply();
    }

    public void oops() {
        currentState = null;
        FLASHRED_CONTINUOUS.apply();
    }

}
