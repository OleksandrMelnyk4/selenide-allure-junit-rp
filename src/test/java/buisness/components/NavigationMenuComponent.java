package buisness.components;

import buisness.core.BasePage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class NavigationMenuComponent extends BasePage {
  private static final String MENU_ITEM_TEMPLATE = ".//div[contains(@class, 'sidebarButton__sidebar-nav-btn')]//a[contains(@href, " +
    "'/%s')]";

  public void navigateToMenu(final String menuItemText) {
    $x(MENU_ITEM_TEMPLATE.formatted(menuItemText)).shouldBe(visible, enabled).click();
    isPageLoaded();
  }
}
