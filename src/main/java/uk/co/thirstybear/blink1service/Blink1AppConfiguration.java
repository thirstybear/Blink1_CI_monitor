package uk.co.thirstybear.blink1service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class Blink1AppConfiguration extends Configuration {
    @JsonProperty
    public final String id;

    public Blink1AppConfiguration(String id) {
        this.id = id;
    }
}
