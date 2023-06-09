package tests;

import core.dto.LoginUserDto;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.pages.DashboardPage;
import tests.pages.LoginPage;
import tests.utils.TakeScreenShotOnFailure;

import static buisness.service.LoginService.getUser;
import static core.enums.PropertyNames.BASE_URL;
import static core.enums.UsersRole.ADMINISTRATOR;
import static core.utils.services.ConfigurationService.getProperty;
import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
public class ReportPortalTests {
  private static WebDriver driver;
  @RegisterExtension
  TakeScreenShotOnFailure watcher = new TakeScreenShotOnFailure(driver);

  @BeforeEach
  public void createDriver() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get(getProperty(BASE_URL.getName()));
    driver.manage().window().maximize();
  }

  @AfterEach
  public void closeDriver() {
    driver.manage().timeouts().implicitlyWait(0, SECONDS);
    driver.quit();
  }

  @Test
  public void isAbleToResizeWidgetsWitActions() {
    LoginUserDto user = getUser(ADMINISTRATOR.getName());
    LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);
    loginPage.loginToReportPortal(user.getUserName(), user.getUserPassword());
    dashboardPage.waitTillPageLoaded();

    dashboardPage.navigateToDashboardWidgets();
    dashboardPage.resizeWidget("LAUNCH STATISTICS BAR");
  }

  @Test
  public void isAbleToScrollToElementWithActionAndJs() {
    String widgetName = "FLAKY TEST CASES";
    LoginUserDto user = getUser(ADMINISTRATOR.getName());
    LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);
    loginPage.loginToReportPortal(user.getUserName(), user.getUserPassword());
    dashboardPage.waitTillPageLoaded();

    dashboardPage.navigateToDashboardWidgets();
    dashboardPage.scrollToTheWidget(widgetName);
    dashboardPage.isWidgetVisible(widgetName);
    dashboardPage.navigateToTheWidgetVisible(widgetName);
  }
}
