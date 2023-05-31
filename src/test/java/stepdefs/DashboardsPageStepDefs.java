package stepdefs;


import buisness.core.pages.DashboardPage;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DashboardsPageStepDefs {
  private final DashboardPage dashboardPage = new DashboardPage();

  @Then("Dashboard page is opened")
  public void dashboardPageIsOpened() {
    dashboardPage.dashboardPageShouldBeOpened();
  }

}
