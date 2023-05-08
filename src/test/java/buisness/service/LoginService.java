package buisness.service;

import buisness.core.DashboardPage;
import buisness.core.LoginPage;
import com.codeborne.selenide.Selenide;
import core.dto.LoginUserDto;
import core.enums.UsersRole;
import lombok.extern.slf4j.Slf4j;

import static core.enums.PropertyNames.BASE_URL;
import static core.utils.services.ConfigurationService.getProperty;

@Slf4j
public class LoginService {
  private final LoginPage loginPage = new LoginPage();
  private final DashboardPage dashboardPage = new DashboardPage();

  private void openLoginPage() {
    Selenide.open(getProperty(BASE_URL.getName()));
  }

  private LoginUserDto getUser(String userRole) {
    return new LoginUserDto(getProperty("usersParams.%s.login".formatted(userRole)), getProperty("usersParams.%s.password".formatted(userRole)));
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
