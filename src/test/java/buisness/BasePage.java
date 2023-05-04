package buisness;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public abstract class BasePage {
  private static final String SPINNER = ".//div[contains(@class, 'spinningPreloader__spinning-preloader')]";

  public void isPageLoaded() {
    $x(SPINNER).shouldNotBe(visible);
  }
}
