package buisness;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LauncherPage extends BasePage {
  private static final String FILTER_PANEL = "//div[contains(@class, 'launchFiltersToolbar__launch-filters-toolbar')]";
  private static final String INPUT_LAUNCH_NAME_FIELD = ".//input[@placeholder = 'Enter name']";
  private static final String SAVE_BUTTON = ".//span[text()= 'Save']";

  public void filtersPanelIsVisible() {
    $x(FILTER_PANEL).shouldBe(visible);
  }

  public void sendTextToLaunchNameInput(final String launcherName) {
    $x(INPUT_LAUNCH_NAME_FIELD).shouldBe(visible).setValue(launcherName);
  }

  public void clickSaveFilterButton() {
    $x(SAVE_BUTTON).shouldBe(visible, enabled).click();
  }


}
