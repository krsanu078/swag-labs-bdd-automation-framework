package com.seleniumforge.bdd.hooks;

import com.seleniumforge.base.BaseTest;
import com.seleniumforge.config.ConfigReader;
import com.seleniumforge.listeners.TestListener;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScenarioHooks extends BaseTest {

    @Before
    public void startScenario() {
        setUp();
    }

    @After
    public void finishScenario(Scenario scenario) {
        try {
            ConfigReader config = ConfigReader.getInstance();
            boolean captureOnSuccess = Boolean.parseBoolean(
                    config.getProperty("report.captureOnSuccess", "true"));
            boolean captureOnSkip = Boolean.parseBoolean(
                    config.getProperty("report.captureOnSkip", "true"));
            boolean shouldCapture = scenario.isFailed()
                    || scenario.getStatus() == Status.SKIPPED && captureOnSkip
                    || scenario.getStatus() == Status.PASSED && captureOnSuccess;
            if (shouldCapture && driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                String title = switch (scenario.getStatus()) {
                    case FAILED -> "Failure screenshot";
                    case SKIPPED -> "Skipped scenario screenshot";
                    default -> "Scenario screenshot";
                };
                scenario.attach(screenshot, "image/png", title);
                TestListener.attachScenarioScreenshot(screenshot, title);
            }
        } finally {
            tearDown();
        }
    }
}
