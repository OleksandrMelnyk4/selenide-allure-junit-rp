package stepdefs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CucumberHook {

  @Before
  public void beforeScenario(Scenario scenario) {
    WebDriverManager.chromedriver().setup();

    Configuration.browser = "chrome";
    Configuration.driverManagerEnabled = true;
    Configuration.browserSize = "1920x1080";
    Configuration.headless = false;
    Configuration.webdriverLogsEnabled = true;
    log.info("START SCENARIO: " + scenario.getName());
  }

  @After
  public void afterScenario(Scenario scenario) {
    log.info("FINISH SCENARIO: " + scenario.getName());
    Selenide.clearBrowserCookies();
    Selenide.closeWindow();
    Selenide.closeWebDriver();
  }

  @After("@filter_is_great")
  public void describingFiltersPage() {
    log.info("As we mentioned, our Filters page look awesome!");
  }
}
