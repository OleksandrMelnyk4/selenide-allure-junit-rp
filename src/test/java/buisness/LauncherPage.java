package buisness;

import buisness.core.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LauncherPage extends BasePage {
  private static final String FILTER_PANEL = "//div[contains(@class, 'launchFiltersToolbar__launch-filters-toolbar')]";

  public void filtersPanelIsVisible() {
    $x(FILTER_PANEL).shouldBe(visible);
  }

}
