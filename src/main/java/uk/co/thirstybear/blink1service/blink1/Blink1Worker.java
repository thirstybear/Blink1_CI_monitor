package uk.co.thirstybear.blink1service.blink1;

import uk.co.thirstybear.blink1service.blink1.actions.FlashRedAction;
import uk.co.thirstybear.blink1service.blink1.actions.SolidRedAction;

public class Blink1Worker {

    public void buildFailed() {
        new FlashRedAction().invoke();
        new SolidRedAction().invoke();
    }

}
