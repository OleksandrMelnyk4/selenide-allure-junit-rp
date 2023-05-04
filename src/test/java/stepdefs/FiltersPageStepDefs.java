package stepdefs;

import buisness.FiltersPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class FiltersPageStepDefs {
  FiltersPage filtersPage = new FiltersPage();

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
}
