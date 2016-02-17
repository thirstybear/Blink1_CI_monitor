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
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;


public class Fixtures {

    public static final DropwizardTestSupport SUPPORT =
            new DropwizardTestSupport(Blink1Application.class, resourceFilePath("blinkapp-config.yaml"));
    private WireMockServer mockJenkinsServer;

    @Before
    public void startBlinkServer() {
        SUPPORT.before();
    }

    @Before
    public void startMockJenkinsServer() {
        mockJenkinsServer = new WireMockServer(wireMockConfig().port(8888));
        mockJenkinsServer.start();
    }

    @After
    public void stopBlinkServer() {
        SUPPORT.after();
    }

    @After
    public void stopMockJenkinsServer() {
        mockJenkinsServer.stop();
    }

    @Given("^a Jenkins server$")
    public void a_Jenkins_server() throws Throwable {
        // TODO refactor this step out? Doesn't do much!
    }

    @When("^the build is clean$")
    public void the_build_is_clean() throws Throwable {
        setMockJenkinsToReturn("blue");
    }

    @Then("^the light is green$")
    public void the_light_is_green() throws Throwable {
        Client client = new JerseyClientBuilder().build();
        Response response = client.target(
                String.format("http://localhost:%d/jenkins", SUPPORT.getLocalPort()))
                .request()
                .get();

        assertEquals("{\"pattern\":\"build_ok\"}", toString(response), true);
    }

    @When("^the build is broken$")
    public void the_build_is_broken() throws Throwable {
        setMockJenkinsToReturn("red");
    }

    @Then("^the light is red$")
    public void the_light_is_red() throws Throwable {
        Client client = new JerseyClientBuilder().build();
        Response response = client.target(
                String.format("http://localhost:%d/jenkins", SUPPORT.getLocalPort()))
                .request()
                .get();

        assertEquals("{\"pattern\":\"build_broken\"}", toString(response), true);
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
