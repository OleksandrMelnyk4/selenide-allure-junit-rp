package tests;

import buisness.DashboardPage;
import buisness.FiltersPage;
import buisness.LoginPage;
import buisness.components.NavigationMenuComponent;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static core.enums.UsersRole.ADMINISTRATOR;
import static core.utils.constants.MenuNames.FILTERS;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
class Tests extends BaseTest {

  LoginPage login = new LoginPage();
  DashboardPage dashboardPage = new DashboardPage();
  FiltersPage filtersPage = new FiltersPage();
  NavigationMenuComponent navigationMenuComponent = new NavigationMenuComponent();

  @Test
  @Description("Admin user is able to login and open Filters page")
  void openLoginPageAsAdmin() {
    login.loginWithUser(ADMINISTRATOR);
    dashboardPage.dashboardPageShouldBeOpened();
    navigationMenuComponent.navigateToMenu(FILTERS);
    filtersPage.filtersPageShouldBeOpened();

    String session = Selenide.sessionId().toString();
    assertFalse(session.isEmpty());
    log.info("Test passed and session is %s".formatted(session));
  }
}
