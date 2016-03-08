package uk.co.thirstybear.blink1service.jenkins;

public class JenkinsViewException extends RuntimeException {
    public JenkinsViewException(Exception e) {
        super(e);
    }
}
