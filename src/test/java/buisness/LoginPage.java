package buisness;

import com.codeborne.selenide.Selenide;
import core.ConfigProvider;
import core.dto.LoginUserDto;
import core.enums.Users;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static core.ConfigProvider.getLogin;
import static core.ConfigProvider.getPassword;

@Slf4j
public class LoginPage extends BasePage {
  private static final String LOGIN_FIELD_XPATH = "//input[@name='login']";
  private static final String PASSWORD_FIELD_XPATH = "//input[@name='password']";
  private static final String LOGIN_BUTTON_XPATH = "//button[@type='submit']";


  private void openLoginPage() {
    Selenide.open(ConfigProvider.URL);
  }

  public void loginWithUser(Users userRole) {
    openLoginPage();
    isPageLoaded();
    LoginUserDto user = getUser(userRole.getName());

    $x(LOGIN_FIELD_XPATH).shouldBe(visible, enabled).setValue(user.getUserName());
    $x(PASSWORD_FIELD_XPATH).shouldBe(visible, enabled).setValue(user.getUserPassword());
    $x(LOGIN_BUTTON_XPATH).shouldBe(visible, enabled).click();

    log.info("Report portal is opened for user: %s".formatted(user.getUserName()));
  }

  private LoginUserDto getUser(String userRole) {
    return new LoginUserDto(getLogin(userRole), getPassword(userRole));
  }
}
