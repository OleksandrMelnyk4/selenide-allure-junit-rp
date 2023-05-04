package stepdefs;

import buisness.LoginPage;
import core.enums.UsersRole;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPageStepDefs {
  LoginPage login = new LoginPage();

  @Given("{} login to the Report Portal")
  public void userLoginToReportPortal(final UsersRole userRole) {
    login.loginWithUser(userRole.toString());
  }
}
