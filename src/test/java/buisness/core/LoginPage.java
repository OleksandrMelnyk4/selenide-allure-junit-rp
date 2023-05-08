package buisness.core;

import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;


@Slf4j
public class LoginPage extends BasePage {
  private static final String LOGIN_FIELD_XPATH = "//input[@name='login']";
  private static final String PASSWORD_FIELD_XPATH = "//input[@name='password']";
  private static final String LOGIN_BUTTON_XPATH = "//button[@type='submit']";

  public void setValueToLoginField(final String login) {
    $x(LOGIN_FIELD_XPATH).shouldBe(visible, enabled).setValue(login);
  }

  public void setValueToPasswordField(final String password) {
    $x(PASSWORD_FIELD_XPATH).shouldBe(visible, enabled).setValue(password);
  }

  public void pressLoginButton() {
    $x(LOGIN_BUTTON_XPATH).shouldBe(visible, enabled).click();
  }
}
