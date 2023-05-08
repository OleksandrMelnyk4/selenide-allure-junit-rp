package tests;

import buisness.components.NavigationMenuComponent;
import buisness.core.DashboardPage;
import buisness.core.FiltersPage;
import buisness.service.LoginService;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static core.enums.UsersRole.ADMINISTRATOR;
import static core.utils.constants.MenuNames.FILTERS;

@Slf4j
class LoginTest extends BaseTest {

  private final LoginService loginService = new LoginService();
  private final DashboardPage dashboardPage = new DashboardPage();
  private final FiltersPage filtersPage = new FiltersPage();
  private final NavigationMenuComponent navigationMenuComponent = new NavigationMenuComponent();

  @Test
  @Description("Admin user is able to login and open Filters page")
  void openLoginPageAsAdmin() {

    loginService.loginWithUser(ADMINISTRATOR);
    dashboardPage.dashboardPageShouldBeOpened();
    navigationMenuComponent.navigateToMenu(FILTERS);
    filtersPage.filtersPageShouldBeOpened();

    String session = Selenide.sessionId().toString();
    assertFalse(session.isEmpty());
    log.info("Test passed and session is %s".formatted(session));
  }
}
