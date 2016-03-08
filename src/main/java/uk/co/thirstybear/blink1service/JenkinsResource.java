package uk.co.thirstybear.blink1service;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/jenkins")
@Produces(MediaType.APPLICATION_JSON)
public class JenkinsResource {
    @GET
    @Timed
    public String getBuildStatus(@QueryParam("url") String targetUrl) {
        try {
            final JenkinsView jenkinsView = new JenkinsView(targetUrl + "/api/json");
            if (jenkinsView.status() == JenkinsState.FAIL) {
                return "{\"pattern\":\"build_broken\"}";
            } else {
                return  "{\"pattern\":\"build_ok\"}";
            }
        } catch (Exception e) {
            return "{\"pattern\":\"build_error\"}";
        }
    }
}
