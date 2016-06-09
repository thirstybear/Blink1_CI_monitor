package uk.co.thirstybear.blink1jenkins;

import uk.co.thirstybear.blink1jenkins.blink1.Blink1Worker;
import uk.co.thirstybear.blink1jenkins.jenkins.JenkinsState;
import uk.co.thirstybear.blink1jenkins.jenkins.JenkinsView;
import uk.co.thirstybear.blink1jenkins.jenkins.JenkinsViewException;

import java.util.Timer;
import java.util.TimerTask;

class ServerPoller extends TimerTask {
    private static final int RUN_IMMEDIATELY = 0;
    private static final int RUN_EVERY_30s = 30000;
    private final JenkinsView jenkinsView;
    private final Blink1Worker blink1Worker;

    private ServerPoller(String jenkinsUrlString) {
        this(new JenkinsView(jenkinsUrlString), new Blink1Worker());
    }

    ServerPoller(JenkinsView jenkinsView, Blink1Worker blink1Worker) {
        this.jenkinsView = jenkinsView;
        this.blink1Worker = blink1Worker;
    }

    @Override
    public void run() {
        try {
            if (jenkinsView.status().equals(JenkinsState.PASS)) {
                blink1Worker.buildPassed();
            } else {
                blink1Worker.buildFailed();
            }
        } catch (JenkinsViewException e) {
            blink1Worker.oops();
        }
    }

    public static void main(String[] args) {

        for(String arg: args) {
            System.out.println(arg);
        }

        String ciServerUrl = args[0];

        final ServerPoller serverPoller = new ServerPoller(ciServerUrl);

        final Timer timer = new Timer();
        timer.schedule(serverPoller, RUN_IMMEDIATELY, RUN_EVERY_30s);
    }

}
