package buisness.core;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static core.utils.Timeout.FIVE_SEC;
import static java.time.Duration.ofMillis;

public abstract class BasePage {
  private static final String SPINNER = ".//div[contains(@class, 'spinningPreloader__spinning-preloader')]";

  public void isPageLoaded() {
    $x(SPINNER).shouldNotBe(visible, ofMillis(FIVE_SEC.getValue()));
  }
}
