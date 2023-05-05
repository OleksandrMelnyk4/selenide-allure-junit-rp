package stepdefs;

import buisness.FiltersPage;
import buisness.LauncherPage;
import buisness.components.AddFilterComponent;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

@Slf4j
public class FiltersPageStepDefs {
  FiltersPage filtersPage = new FiltersPage();
  LauncherPage launcherPage = new LauncherPage();
  AddFilterComponent addFilterComponent = new AddFilterComponent();

  @Then("Filters grid contains such filters")
  public void filterGridContainsSuchFilters(final DataTable dataTable) {
    filtersPage.filtersPageShouldBeOpened();
    filtersPage.gridShouldContainsFilters();
    List<String> filterNames = dataTable.asList();
    filterNames.forEach(e -> filtersPage.filterByNameShouldBeVisible(e));
  }

  @Then("Filters grid contains such columns")
  public void filterGridContainsSuchColumns(final DataTable dataTable) {
    filtersPage.filtersPageShouldBeOpened();
    filtersPage.gridShouldContainsFilters();
    List<String> filterNames = dataTable.asList();
    filterNames.forEach(e -> filtersPage.mainGridContainsColumns(e));
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
