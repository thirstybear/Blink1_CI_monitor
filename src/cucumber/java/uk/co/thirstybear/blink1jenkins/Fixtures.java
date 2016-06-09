package uk.co.thirstybear.blink1jenkins;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.co.thirstybear.blink1jenkins.blink1.Blink1Worker;
import uk.co.thirstybear.blink1jenkins.jenkins.JenkinsResponse;
import uk.co.thirstybear.blink1jenkins.jenkins.JenkinsSingleJobResponse;
import uk.co.thirstybear.blink1jenkins.jenkins.JenkinsView;
import uk.co.thirstybear.blink1jenkins.jenkins.JenkinsViewResponse;

import javax.ws.rs.core.Response;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.Integer.parseInt;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


public class Fixtures {

    private WireMockServer mockJenkinsServer;
    private String responseAsString;
    private String targetJenkinsUrl;
    private JenkinsResponse mockJenkinsResponse;

    @After
    public void stopMockJenkinsServer() {
        if (mockJenkinsServer != null) {
            mockJenkinsServer.stop();
            mockJenkinsServer = null;
        }
    }

    @Given("^a Jenkins job at ([^\"]*)$")
    public void a_Jenkins_job_at(String jenkinsUrl) throws Throwable {
        mockJenkinsResponse = new JenkinsSingleJobResponse();
        setUpMockJenkins(jenkinsUrl);
    }

    @Given("^a Jenkins view at ([^\"]*)$")
    public void a_Jenkins_view_at(String jenkinsUrl) throws Throwable {
        mockJenkinsResponse = new JenkinsViewResponse();
        setUpMockJenkins(jenkinsUrl);
    }

    @When("^the build is clean$")
    public void the_build_is_clean() throws Throwable {
        setMockJenkinsToReturn("blue");
    }

    @Then("^the light is green$")
    public void the_light_is_green() throws Throwable {
        Blink1Worker mockBlink1Worker = mock(Blink1Worker.class);
        new ServerPoller(new JenkinsView(targetJenkinsUrl), mockBlink1Worker).run();

        verify(mockBlink1Worker).buildPassed();
        verifyNoMoreInteractions(mockBlink1Worker);
    }

    @Given("^the build is broken$")
    public void the_build_is_broken() throws Throwable {
        setMockJenkinsToReturn("red");
    }

    @Then("^the light is red$")
    public void the_light_is_red() throws Throwable {
        Blink1Worker mockBlink1Worker = mock(Blink1Worker.class);
        new ServerPoller(new JenkinsView(targetJenkinsUrl), mockBlink1Worker).run();

        verify(mockBlink1Worker).buildFailed();
        verifyNoMoreInteractions(mockBlink1Worker);
    }


    @Then("^the light flashes red$")
    public void theLightFlashesRed() throws Throwable {
        Blink1Worker mockBlink1Worker = mock(Blink1Worker.class);
        new ServerPoller(new JenkinsView(targetJenkinsUrl), mockBlink1Worker).run();

        verify(mockBlink1Worker).oops();
        verifyNoMoreInteractions(mockBlink1Worker);
    }

    private String toString(Response response) {
        return response.readEntity(String.class);
    }

    private void setUpMockJenkins(String jenkinsUrl) {
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

    private void setMockJenkinsToReturn(final String buildColor) {
        mockJenkinsServer.stubFor(get(urlPathMatching(".*/api/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(mockJenkinsResponse.getResponse(buildColor))));
    }

 }
