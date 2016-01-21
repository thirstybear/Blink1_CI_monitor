package uk.co.thirstybear.blink1service;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.dropwizard.testing.DropwizardTestSupport;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;


public class Fixtures {

    public static final DropwizardTestSupport SUPPORT =
            new DropwizardTestSupport(Blink1Application.class, resourceFilePath("blinkapp-config.yaml"));

    @Before
    public void startServer() {
        SUPPORT.before();
    }

    @After
    public void stopServer() {
        SUPPORT.after();
    }

    @Given("^a Jenkins server$")
    public void a_Jenkins_server() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("^the build is clean$")
    public void the_build_is_clean() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^the light is green$")
    public void the_light_is_green() throws Throwable {
        Client client = new JerseyClientBuilder().build();
        Response response = client.target(
                String.format("http://localhost:%d/jenkins", SUPPORT.getLocalPort()))
                .request()
                .get();

        assertEquals("{\"pattern\":\"build_passing\"}", response.readEntity(String.class), true);
    }
}
