package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static core.enums.PropertyNames.BROWSER;
import static core.enums.PropertyNames.BROWSER_SIZE;
import static core.enums.PropertyNames.DRIVER_MANAGER_ENABLED;
import static core.enums.PropertyNames.HEADLESS;
import static core.enums.PropertyNames.WEB_DRIVER_LOGS_ENABLED;
import static core.utils.services.ConfigurationService.getProperty;
import static java.lang.Boolean.parseBoolean;

abstract public class BaseTest {

  @BeforeAll
  public static void init() {
    setUp();
  }

  public static void setUp() {
    Configuration.browser = getProperty(BROWSER.getName());
    Configuration.driverManagerEnabled = parseBoolean(getProperty(DRIVER_MANAGER_ENABLED.getName()));
    Configuration.browserSize = getProperty(BROWSER_SIZE.getName());
    Configuration.headless = parseBoolean(getProperty(HEADLESS.getName()));
    Configuration.webdriverLogsEnabled = parseBoolean(getProperty(WEB_DRIVER_LOGS_ENABLED.getName()));
  }

  @AfterEach
  public void tearDown() {
    Selenide.clearBrowserCookies();
    Selenide.closeWebDriver();
  }
}
