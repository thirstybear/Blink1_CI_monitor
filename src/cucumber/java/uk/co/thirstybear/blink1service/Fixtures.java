package uk.co.thirstybear.blink1service;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.dropwizard.testing.DropwizardTestSupport;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static java.lang.Integer.parseInt;
import static org.junit.Assert.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;


public class Fixtures {

    public static final DropwizardTestSupport SUPPORT =
            new DropwizardTestSupport(Blink1Application.class, resourceFilePath("blinkapp-config.yaml"));
    private WireMockServer mockJenkinsServer;
    private String responseAsString;
    private String targetJenkinsUrl;

    @Before
    public void startBlinkServer() {
        SUPPORT.before();
    }

    @After
    public void stopBlinkServer() {
        SUPPORT.after();
    }

    @After
    public void stopMockJenkinsServer() {
        if (mockJenkinsServer != null) {
            mockJenkinsServer.stop();
            mockJenkinsServer = null;
        }
    }

    @Given("^a Jenkins server at ([^\"]*)$")
    public void a_Jenkins_server_at(String jenkinsUrl) throws Throwable {
        targetJenkinsUrl = jenkinsUrl;
        final String[] strings = jenkinsUrl.split(":");
        if (strings.length != 2) {
            fail ("Format for host needs to be <ip>:<port>");
        }

        if (mockJenkinsServer != null) {
            stopMockJenkinsServer();
        }

        if ("localhost".equals(strings[0])) {
            mockJenkinsServer = new WireMockServer(wireMockConfig().port(parseInt(strings[1].split("/")[0])));
            mockJenkinsServer.start();
        }
    }

    @Given("^the build is clean$")
    public void the_build_is_clean() throws Throwable {
        setMockJenkinsToReturn("blue");
    }

    @Then("^the light is green$")
    public void the_light_is_green() throws Throwable {
        Client client = new JerseyClientBuilder().build();
        Response response = client.target(
                String.format("http://localhost:%d/jenkins?url=%s", SUPPORT.getLocalPort(), targetJenkinsUrl))
                .request()
                .get();

        assertEquals("{\"pattern\":\"build_ok\"}", toString(response), true);
    }

    @Given("^the build is broken$")
    public void the_build_is_broken() throws Throwable {
        setMockJenkinsToReturn("red");
    }

    @When("^the Blink server is queried$")
    public void the_Blink_server_is_queried() throws Throwable {
        Client client = new JerseyClientBuilder().build();
        Response response = client.target(
                String.format("http://localhost:%d/jenkins?url=%s", SUPPORT.getLocalPort(), targetJenkinsUrl))
                .request()
                .get();

        responseAsString = toString(response);
    }


    @Then("^the light is red$")
    public void the_light_is_red() throws Throwable {
        assertEquals("{\"pattern\":\"build_broken\"}", responseAsString, true);
    }

    @Then("^the light flashes red$")
    public void theLightFlashesRed() throws Throwable {
        assertEquals("{\"pattern\":\"build_error\"}", responseAsString, true);

    }

    private String toString(Response response) {
        return response.readEntity(String.class);
    }

    private void setMockJenkinsToReturn(final String buildColor) {
        mockJenkinsServer.stubFor(get(urlPathMatching(".*/api/json\\?tree=color"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"color\":\"" + buildColor + "\"}")));
    }

 }
