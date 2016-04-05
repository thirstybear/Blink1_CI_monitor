package uk.co.thirstybear.blink1service.jenkins;

public class JenkinsViewResponse implements JenkinsResponse {
    private final static String json = "{\n" +
            "  \"description\": null,\n" +
            "  \"jobs\": [\n" +
            "    {\n" +
            "      \"name\": \"01-job-from-template-00\",\n" +
            "      \"url\": \"http:\\/\\/localhost:8080\\/job\\/01-job-from-template-00\\/\",\n" +
            "      \"color\": \"blue\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"test\",\n" +
            "      \"url\": \"http:\\/\\/localhost:8080\\/job\\/test\\/\",\n" +
            "      \"color\": \"%s\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"name\": \"View\",\n" +
            "  \"property\": [\n" +
            "    \n" +
            "  ],\n" +
            "  \"url\": \"http:\\/\\/localhost:8080\\/view\\/View\\/\"\n" +
            "}";

    @Override
    public String getResponse(String state) {
        return String.format(json, state);
    }
}
