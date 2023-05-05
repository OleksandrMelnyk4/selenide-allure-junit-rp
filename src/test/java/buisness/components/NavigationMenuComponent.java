package buisness.components;

import buisness.BasePage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static core.utils.Timeout.FIVE_SEC;
import static java.time.Duration.ofMillis;

public class NavigationMenuComponent extends BasePage {
  private static final String MENU_ITEM_TEMPLATE = ".//div[contains(@class, 'sidebarButton__sidebar-nav-btn')]//a[contains(@href, " +
    "'/%s')]";
  private static final String USER_BLOCK = ".//div[contains(@class, 'userBlock__avatar-wrapper')]";
  private static final String USER_MENU_ITEM_TEMPLATE = ".//div[text()='%s']";

  public void navigateToMenu(final String menuItemText) {
    $x(MENU_ITEM_TEMPLATE.formatted(menuItemText)).shouldBe(visible, enabled).click();
    isPageLoaded();
  }

  public void clickOnUserIconMenu() {
    $x(USER_BLOCK).shouldBe(visible, ofMillis(FIVE_SEC.getValue())).click();
  }

  public void clickOnUserMenuItem(final String menuName) {
    $x(USER_MENU_ITEM_TEMPLATE.formatted(menuName)).shouldBe(visible).click();
  }
}
