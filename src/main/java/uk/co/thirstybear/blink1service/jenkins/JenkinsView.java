package uk.co.thirstybear.blink1service.jenkins;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import static uk.co.thirstybear.blink1service.jenkins.JenkinsState.FAIL;
import static uk.co.thirstybear.blink1service.jenkins.JenkinsState.PASS;


public class JenkinsView {

    private final UrlReader urlReader;

    public JenkinsView (String url) {
        this(new UrlReader(url + "/api/json"));
    }

    JenkinsView(UrlReader urlReader) {
        this.urlReader = urlReader;
    }

    public JenkinsState status() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(urlReader.get());
            List<JsonNode> buildStatuses = root.findValues("color");

            for (JsonNode buildStatus : buildStatuses) {
                if ("red".equals(buildStatus.textValue())) {
                    return FAIL;
                }
            }
            return PASS;
        } catch (IOException ioException) {
            throw new JenkinsViewException(ioException);
        }
    }
}
