package buisness;

import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static core.utils.Timeout.TEN_SEC;
import static java.time.Duration.ofMillis;

@Slf4j
public class DashboardPage extends BasePage {
  private static final String PAGE_TITLE_XPATH = "//span[@title = 'All Dashboards']";

  public void dashboardPageShouldBeOpened() {
    $x(PAGE_TITLE_XPATH).shouldBe(visible, ofMillis(TEN_SEC.getValue()));
    isPageLoaded();
    log.info("Home page is opened");
  }
}
