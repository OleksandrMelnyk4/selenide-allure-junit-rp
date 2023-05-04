package tests;

import buisness.DashboardPage;
import buisness.FiltersPage;
import buisness.LoginPage;
import buisness.components.NavigationMenuComponent;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static core.enums.UsersRole.ADMINISTRATOR;
import static core.utils.constants.MenuNames.FILTERS;

class Tests extends BaseTest {

  private final LoginPage login = new LoginPage();
  private final DashboardPage dashboardPage = new DashboardPage();
  private final FiltersPage filtersPage = new FiltersPage();
  private final NavigationMenuComponent navigationMenuComponent = new NavigationMenuComponent();

  @Test
  @Description("Admin user is able to login and open Filters page")
  void openLoginPageAsAdmin() {
    login.loginWithUser(ADMINISTRATOR);
    dashboardPage.dashboardPageShouldBeOpened();
    navigationMenuComponent.navigateToMenu(FILTERS);
    filtersPage.filtersPageShouldBeOpened();
  }
}
