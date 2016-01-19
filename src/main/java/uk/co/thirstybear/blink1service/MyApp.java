package uk.co.thirstybear.blink1service;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class MyApp extends Application<MyConfiguration> {
    @Override
    public void run(MyConfiguration configuration, Environment environment) throws Exception {
        final JenkinsResource jenkinsResource = new JenkinsResource();
        environment.jersey().register(jenkinsResource);
    }

    public static void main(String[] args) throws Exception {
        new MyApp().run(args);
    }
}
