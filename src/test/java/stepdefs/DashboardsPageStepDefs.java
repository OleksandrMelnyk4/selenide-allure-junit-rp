package stepdefs;

import buisness.DashboardPage;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DashboardsPageStepDefs {
  DashboardPage dashboardPage = new DashboardPage();

  @Then("Dashboard page is opened")
  public void dashboardPageIsOpened() {
    dashboardPage.dashboardPageShouldBeOpened();
  }

}
