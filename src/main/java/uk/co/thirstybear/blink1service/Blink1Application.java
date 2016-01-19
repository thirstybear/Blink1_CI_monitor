package uk.co.thirstybear.blink1service;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class Blink1Application extends Application<Blink1AppConfiguration> {
    @Override
    public void run(Blink1AppConfiguration configuration, Environment environment) throws Exception {
        final JenkinsResource jenkinsResource = new JenkinsResource();
        environment.jersey().register(jenkinsResource);
    }

    public static void main(String[] args) throws Exception {
        new Blink1Application().run(args);
    }
}
