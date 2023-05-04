package buisness;

import core.dto.LoginUserDto;
import core.enums.UsersRole;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static core.utils.services.ConfigurationService.getUser;


@Slf4j
public class LoginPage extends BasePage {
  private HomePage homePage = new HomePage();
  private static final String LOGIN_FIELD_XPATH = "//input[@name='login']";
  private static final String PASSWORD_FIELD_XPATH = "//input[@name='password']";
  private static final String LOGIN_BUTTON_XPATH = "//button[@type='submit']";

  public void loginWithUser(String userRole) {
    homePage.openHomePage();
    isPageLoaded();
    LoginUserDto user = getUser(userRole);

    $x(LOGIN_FIELD_XPATH).shouldBe(visible, enabled).setValue(user.getUserName());
    $x(PASSWORD_FIELD_XPATH).shouldBe(visible, enabled).setValue(user.getUserPassword());
    $x(LOGIN_BUTTON_XPATH).shouldBe(visible, enabled).click();

    log.info("Report portal is opened for user: %s".formatted(user.getUserName()));
  }
}
