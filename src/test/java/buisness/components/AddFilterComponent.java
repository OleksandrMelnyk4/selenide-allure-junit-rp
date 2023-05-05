package buisness.components;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class AddFilterComponent {

  private static final String NAME_FIELD = ".//input[@placeholder = 'Enter filter name']";
  private static final String ADD_BUTTON = ".//button[text() = 'Add']";
  private static final String SUCCESS_ADDING_FILTER_NOTIFICATION = "//p[text() = 'Filter has been saved']";

  public void setFilterName(final String filterName) {
    $x(NAME_FIELD).shouldBe(visible, enabled).setValue(filterName);
  }

  public void clickAddButton() {
    $x(ADD_BUTTON).shouldBe(visible, enabled).click();
  }

  public void successAddingFilterNotificationShouldBeVisible() {
    $x(SUCCESS_ADDING_FILTER_NOTIFICATION).shouldBe(visible);
  }
}
