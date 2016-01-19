package uk.co.thirstybear.blink1service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class MyConfiguration extends Configuration {
    @JsonProperty
    public final String id;

    public MyConfiguration(String id) {
        this.id = id;
    }
}
