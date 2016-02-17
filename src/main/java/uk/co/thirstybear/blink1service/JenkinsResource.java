package uk.co.thirstybear.blink1service;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/jenkins")
@Produces(MediaType.APPLICATION_JSON)
public class JenkinsResource {
    @GET
    @Timed
    public String getBuildStatus() {
        Client client = new JerseyClientBuilder().build();
        Response response = client.target(
                String.format("http://localhost:%d/job/test/api/json?tree=color", 8888))
                .request()
                .get();

        final String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(jsonResponse);
            JsonNode buildStatus = root.get("color");

            if ("blue".equals(buildStatus.textValue())) {
                return "{\"pattern\":\"build_ok\"}";
            } else {
                return "{\"pattern\":\"build_broken\"}";
            }
        } catch (IOException e) {
            return String.format("{\"error\":\"%s\"}", e.getMessage());
        }



    }
}
