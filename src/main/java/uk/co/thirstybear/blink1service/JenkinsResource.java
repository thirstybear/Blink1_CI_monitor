package uk.co.thirstybear.blink1service;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/jenkins")
@Produces(MediaType.APPLICATION_JSON)
public class JenkinsResource {
    @GET
    @Timed
    public String getBuildStatus() {
        return "wibble";
    }
}
