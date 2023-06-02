package stepdefs;

import buisness.components.AddFilterComponent;
import buisness.core.pages.FiltersPage;
import buisness.core.pages.LauncherPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;

@Slf4j
public class FiltersPageStepDefs {
  private final FiltersPage filtersPage = new FiltersPage();
  private final LauncherPage launcherPage = new LauncherPage();
  private final AddFilterComponent addFilterComponent = new AddFilterComponent();

  @Then("Filters grid contains such filters")
  public void filterGridContainsSuchFilters(final DataTable dataTable) {
    filtersPage.filtersPageShouldBeOpened();
    filtersPage.gridShouldContainsFilters();
    List<String> filterNames = dataTable.asList();
    filterNames.forEach(e ->
      assertTrue("The filter with name %s hasn't been found".formatted(e), filtersPage.filterByNameShouldBeVisible(e))
    );
  }

  @Then("Filters grid contains such columns")
  public void filterGridContainsSuchColumns(final DataTable dataTable) {
    filtersPage.filtersPageShouldBeOpened();
    filtersPage.gridShouldContainsFilters();
    List<String> filterNames = dataTable.asList();
    filterNames.forEach(e ->
      assertTrue("The column %s hasn't been found".formatted(e), filtersPage.mainGridContainsColumns(e))
    );
  }

  @Then("User creates a new filter")
  public void userCreatesNewFilter() {
    String randomName = RandomStringUtils.randomAlphabetic(10);
    filtersPage.clickAddNewFilterButton();
    launcherPage.sendTextToLaunchNameInput(randomName);
    launcherPage.clickSaveFilterButton();
    addFilterComponent.setFilterName(randomName);
    addFilterComponent.clickAddButton();
    addFilterComponent.successAddingFilterNotificationShouldBeVisible();
  }
}
