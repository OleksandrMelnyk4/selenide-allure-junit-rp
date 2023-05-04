package buisness;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static core.utils.services.ConfigurationService.getProperty;

public class HomePage {

  @BeforeEach
  public void init() {
    setUp();
  }

  public void setUp() {
    WebDriverManager.chromedriver().setup();

    Configuration.browser = "chrome";
    Configuration.driverManagerEnabled = true;
    Configuration.browserSize = "1920x1080";
    Configuration.headless = false;
    Configuration.webdriverLogsEnabled = true;
  }

  @AfterEach
  public void tearDown() {
    Selenide.clearBrowserCookies();
    Selenide.closeWebDriver();
  }

  public void openHomePage() {
    Selenide.open(getProperty("base.uri"));
    Selenide.webdriver().driver().getWebDriver().manage().window().maximize();
  }
}
