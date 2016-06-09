package uk.co.thirstybear.blink1jenkins.jenkins;

public class JenkinsViewException extends RuntimeException {
    public JenkinsViewException(Exception e) {
        super(e);
    }
}
