package uk.co.thirstybear.blink1service;

public class JenkinsSingleJobResponse implements JenkinsResponse{
    private static final String json = "{\n" +
            "  \"actions\": [\n" +
            "    \n" +
            "  ],\n" +
            "  \"description\": \"\",\n" +
            "  \"displayName\": \"test\",\n" +
            "  \"displayNameOrNull\": null,\n" +
            "  \"name\": \"test\",\n" +
            "  \"url\": \"http:\\/\\/localhost:8080\\/job\\/test\\/\",\n" +
            "  \"buildable\": true,\n" +
            "  \"builds\": [\n" +
            "    {\n" +
            "      \"number\": 1,\n" +
            "      \"url\": \"http:\\/\\/localhost:8080\\/job\\/test\\/1\\/\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"color\": \"%s\",\n" +
            "  \"firstBuild\": {\n" +
            "    \"number\": 1,\n" +
            "    \"url\": \"http:\\/\\/localhost:8080\\/job\\/test\\/1\\/\"\n" +
            "  },\n" +
            "  \"healthReport\": [\n" +
            "    {\n" +
            "      \"description\": \"Build stability: No recent builds failed.\",\n" +
            "      \"iconClassName\": \"icon-health-80plus\",\n" +
            "      \"iconUrl\": \"health-80plus.png\",\n" +
            "      \"score\": 100\n" +
            "    }\n" +
            "  ],\n" +
            "  \"inQueue\": false,\n" +
            "  \"keepDependencies\": false,\n" +
            "  \"lastBuild\": {\n" +
            "    \"number\": 1,\n" +
            "    \"url\": \"http:\\/\\/localhost:8080\\/job\\/test\\/1\\/\"\n" +
            "  },\n" +
            "  \"lastCompletedBuild\": {\n" +
            "    \"number\": 1,\n" +
            "    \"url\": \"http:\\/\\/localhost:8080\\/job\\/test\\/1\\/\"\n" +
            "  },\n" +
            "  \"lastFailedBuild\": null,\n" +
            "  \"lastStableBuild\": {\n" +
            "    \"number\": 1,\n" +
            "    \"url\": \"http:\\/\\/localhost:8080\\/job\\/test\\/1\\/\"\n" +
            "  },\n" +
            "  \"lastSuccessfulBuild\": {\n" +
            "    \"number\": 1,\n" +
            "    \"url\": \"http:\\/\\/localhost:8080\\/job\\/test\\/1\\/\"\n" +
            "  },\n" +
            "  \"lastUnstableBuild\": null,\n" +
            "  \"lastUnsuccessfulBuild\": null,\n" +
            "  \"nextBuildNumber\": 2,\n" +
            "  \"property\": [\n" +
            "    \n" +
            "  ],\n" +
            "  \"queueItem\": null,\n" +
            "  \"concurrentBuild\": false,\n" +
            "  \"downstreamProjects\": [\n" +
            "    \n" +
            "  ],\n" +
            "  \"scm\": {\n" +
            "    \n" +
            "  },\n" +
            "  \"upstreamProjects\": [\n" +
            "    \n" +
            "  ]\n" +
            "}";


    @Override
    public String getResponse(String state) {
        return String.format(json, state);
    }
}
