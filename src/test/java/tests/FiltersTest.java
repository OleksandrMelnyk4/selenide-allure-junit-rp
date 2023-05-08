package tests;

import buisness.LauncherPage;
import buisness.components.NavigationMenuComponent;
import buisness.core.FiltersPage;
import buisness.service.LoginService;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static core.enums.UsersRole.ADMINISTRATOR;
import static core.utils.constants.MenuNames.FILTERS;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
public class FiltersTest extends BaseTest {
  private final LoginService loginService = new LoginService();
  private final FiltersPage filtersPage = new FiltersPage();
  NavigationMenuComponent navigationMenuComponent = new NavigationMenuComponent();
  LauncherPage launcherPage = new LauncherPage();

  static Stream<Arguments> filterColumnsDataProvider() {
    return Stream.of(
      arguments("Filter name"),
      arguments("Options"),
      arguments("Owner"),
      arguments("Shared"),
      arguments("Display on launches"),
      arguments("Delete"));
  }

  static Stream<Arguments> filterSearchDataProvider() {
    return Stream.of(
      arguments("Android_API"),
      arguments("DEMO_FILTER"),
      arguments("Test"));
  }

  @Test
  @Description("Verify filter grid contains items")
  void filtersPageShouldHaveItems() {
    loginService.loginWithUser(ADMINISTRATOR);
    navigationMenuComponent.navigateToMenu(FILTERS);
    filtersPage.gridShouldContainsFilters();
  }

  @ParameterizedTest(name = "Verify filters grid contains {0} column")
  @MethodSource("filterColumnsDataProvider")
  @Description("Verify filters grid contains proper columns")
  void verifyFilterGridContainsProperColumns(final String column) {
    loginService.loginWithUser(ADMINISTRATOR);
    navigationMenuComponent.navigateToMenu(FILTERS);
    filtersPage.gridShouldContainsFilters();
    filtersPage.mainGridContainsColumns(column);
  }

  @ParameterizedTest(name = "Verify search be {0} name")
  @MethodSource("filterSearchDataProvider")
  @Description("Verify search by Filter name")
  void searchFiltersByNameWorksCorrectly(final String filterName) {
    loginService.loginWithUser(ADMINISTRATOR);
    navigationMenuComponent.navigateToMenu(FILTERS);
    filtersPage.gridShouldContainsFilters();
    filtersPage.applySearchFilterByName(filterName);
    filtersPage.openFilterByName(filterName);
    launcherPage.filtersPanelIsVisible();
  }
}
