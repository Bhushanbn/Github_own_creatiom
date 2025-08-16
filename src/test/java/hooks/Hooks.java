package hooks;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScreenshotUtils;

public class Hooks {

    @Before
    public void beforeScenario() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(config.ConfigReader.get("baseUrl"));
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtils.takeScreenshot();
        }
        DriverFactory.quitDriver();
    }
}
