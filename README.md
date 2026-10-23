# Swag Labs BDD Automation Framework

An end-to-end UI test automation framework for the [Swag Labs demo storefront](https://www.saucedemo.com/). It combines behavior-driven development with reusable Selenium page objects and automated test reporting.

## Project highlights

- **BDD specifications:** Cucumber scenarios express user behavior in Gherkin and are organized by application feature.
- **Layered design:** Step definitions coordinate actions and assertions through page objects; browser setup and cleanup are handled by scenario hooks.
- **Reliable browser interactions:** Selenium explicit waits are used for page and element state, with implicit waits disabled.
- **Scenario-level isolation:** Each scenario receives a fresh WebDriver session.
- **Test execution and reporting:** TestNG runs the Cucumber suite; Cucumber HTML/JSON and ExtentReports capture results. Scenario screenshots are embedded in reports when a browser session is available.
- **Maintainable configuration:** Browser, base URL, timeouts, and screenshot settings are kept in one properties file.

## Technology stack

| Technology                         | Purpose                                |
| ---                                | ---                                    |
| Java 17                            | Framework and test implementation      |
| Selenium WebDriver 4               | Browser automation                     |
| Cucumber 7                         | Gherkin features and BDD glue          |
| TestNG                             | Test execution and listener lifecycle  |
| Maven                              | Dependency and build management        |
| ExtentReports and Cucumber plugins | HTML/JSON results and screenshots      |
| Log4j2                             | Execution logging                      |

## Scenarios covered

The current feature suite covers:

- Login with valid credentials and rejection of invalid/locked-out credentials
- Adding products to the cart and removing products
- Viewing product details
- Completing checkout and verifying the confirmation
- Logging out

Feature files are in [`src/test/resources/features`](src/test/resources/features). The `@smoke` tag selects the valid-login smoke scenario; the other current scenarios use `@regression`.

## Project structure

```text
src/
  main/
    java/com/seleniumforge/
      base/             Shared page and test lifecycle behavior
      config/           Configuration loading
      driver/           WebDriver lifecycle and thread-local storage
      factories/        Browser setup
      listeners/        TestNG reporting integration
      pages/            Page Object Model
      reports/          ExtentReports setup and report archiving
      utilities/        Shared utilities
    resources/
      config.properties Framework and reporting configuration
      log4j2.xml         Logging configuration
  test/
    java/com/seleniumforge/bdd/
      hooks/            Per-scenario browser lifecycle and screenshots
      runners/          Cucumber TestNG runner
      steps/            Gherkin step definitions
    resources/
      features/         Gherkin scenarios
      users.json        Demo application test users
pom.xml
testng.xml
```

## Prerequisites

- JDK 17
- Maven 3.6 or newer
- A supported browser installed locally (Chrome is configured by default)
- Network access to the Swag Labs demo application

Selenium Manager resolves the matching browser driver when the tests start, so a separately downloaded ChromeDriver is not normally required.

## Run the tests

From the project root, run the complete suite:

```powershell
mvn clean test
```

Run only the login smoke scenario:

```powershell
mvn test "-Dcucumber.filter.tags=@smoke"
```

Run the regression scenarios:

```powershell
mvn test "-Dcucumber.filter.tags=@regression"
```

The default browser is visible (not headless). To run headless, set `headless=true` in `src/main/resources/config.properties`. Browser startup, browser availability, and access to the external demo site are required for the UI tests.

## Continuous integration

GitHub Actions runs the complete BDD suite on pushes and pull requests targeting `main`, and can also be started manually from the Actions tab. CI uses Java 17, runs Chrome headlessly, and uploads Cucumber, Extent, and log output as downloadable workflow artifacts (when generated).

To override a configuration value for a single local Maven invocation, pass it as a system property:

```powershell
mvn test "-Dheadless=true"
```

## Configuration

Update `src/main/resources/config.properties` to change the local test settings:

| Property                  | Default                     | Description                    |
| ---                       | ---                         | ---                            |
| `browser`                 | `chrome`                    | Browser to launch              |
| `url`                     | `https://www.saucedemo.com` | Application base URL           |
| `timeouts`                | `30`                        | Page-load timeout in seconds   |
| `explicitWaitTimeout`     | `10`              | Explicit element-wait timeout in seconds |
| `headless`                | `false`           | Run the browser without a visible window |
| `report.captureOnSuccess` | `true`            | Attach screenshots for passing scenarios |
| `report.captureOnSkip`    | `true`            | Attach screenshots for skipped scenarios |
| `report.archiveOnFinish`  | `true`            | Archive the Extent report after the run  |

Failed scenarios are always captured when a browser session is available. The demo credentials used by the scenarios are public Swag Labs test accounts, not credentials for a private service.

## Reports and logs

After a test run, see:

- `target/cucumber-reports/cucumber.html` — Cucumber HTML report
- `target/cucumber-reports/cucumber.json` — Cucumber JSON results
- `reports/ExtentReport_<timestamp>.html` — Extent report with scenario screenshots
- `reports/archive/` — archived Extent reports when report archiving is enabled
- `logs/` — execution logs, if generated by the logging configuration

Build output and generated reports are local artifacts and are excluded from version control.

## How the test flow is organized

1. A Cucumber feature describes the user behavior.
2. Cucumber resolves each Gherkin step to a step definition.
3. Step definitions use page objects for browser actions and assertions.
4. Scenario hooks start the browser before each scenario, attach configured screenshots afterward, and close the browser.
5. The TestNG listener and Cucumber plugins produce the run reports.

This repository targets a public demo storefront for learning and demonstration. It is an automation-framework sample, not a production commerce application.
