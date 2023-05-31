package buisness.service;

import buisness.core.pages.DashboardPage;
import buisness.core.pages.LoginPage;
import com.codeborne.selenide.Selenide;
import core.dto.LoginUserDto;
import core.enums.UsersRole;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.open;
import static core.utils.services.ConfigurationService.getProperty;

@Slf4j
public class LoginService {
  private final LoginPage loginPage = new LoginPage();
  private final DashboardPage dashboardPage = new DashboardPage();

  public static LoginUserDto getUser(String userRole) {
    return new LoginUserDto(getProperty("usersParams.%s.login".formatted(userRole)), getProperty("usersParams.%s.password".formatted(userRole)));
  }

  private void openLoginPage() {
    WebDriverManager.chromedriver().setup();
    open("/");
    Selenide.webdriver().driver().getWebDriver().manage().window().maximize();
  }

  public void loginWithUser(UsersRole userRole) {
    openLoginPage();
    dashboardPage.isPageLoaded();
    LoginUserDto user = getUser(userRole.getName());

    loginPage.setValueToLoginField(user.getUserName());
    loginPage.setValueToPasswordField(user.getUserPassword());
    loginPage.pressLoginButton();

    log.info("Report portal is opened for user: %s".formatted(user.getUserName()));
  }
}
