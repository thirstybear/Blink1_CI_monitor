import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.dropwizard.testing.DropwizardTestSupport;
import uk.co.thirstybear.blink1service.Blink1Application;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;

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


    }

}
