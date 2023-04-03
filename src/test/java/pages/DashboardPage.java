package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofMillis;
import static utils.timeouts.Timeout.TEN_SEC;

public class DashboardPage {
  private static final String HOME_PAGE_TITLE_XPATH = "//span[@title = 'All Dashboards']";

  public void dashboardsPageShouldBeOpened() {
    $x(HOME_PAGE_TITLE_XPATH).shouldBe(visible, ofMillis(TEN_SEC.getValue()));
  }
}
