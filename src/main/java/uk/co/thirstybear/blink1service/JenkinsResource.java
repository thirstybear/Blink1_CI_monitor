package uk.co.thirstybear.blink1service;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/jenkins")
@Produces(MediaType.APPLICATION_JSON)
public class JenkinsResource {
    @GET
    @Timed
    public String getBuildStatus(@QueryParam("url") String targetUrl) {
        try {
            Client client = new JerseyClientBuilder().build();
            Response response = client.target(
                    String.format("http://%s/api/json?tree=color", targetUrl))
                    .request()
                    .get();

            final String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);
            List<JsonNode> buildStatuses = root.findValues("color");

            for (JsonNode buildStatus : buildStatuses) {
                if ("red".equals(buildStatus.textValue())) {
                    return "{\"pattern\":\"build_broken\"}";
                }
            }
        } catch (Exception e) {
            return "{\"pattern\":\"build_error\"}";
        }
        return  "{\"pattern\":\"build_ok\"}";
    }
}
