package stepdefs;

import buisness.service.LoginService;
import core.enums.UsersRole;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPageStepDefs {
  private final LoginService loginService = new LoginService();

  @Given("{} login to the Report Portal")
  public void userLoginToReportPortal(final UsersRole userRole) {
    loginService.loginWithUser(userRole);
  }
}
