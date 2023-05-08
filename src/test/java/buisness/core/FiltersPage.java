package buisness.core;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static core.utils.Timeout.TEN_SEC;
import static java.time.Duration.ofMillis;

@Slf4j
public class FiltersPage extends BasePage {
  private static final String PAGE_TITLE_XPATH = ".//span[@title = 'Filters']";
  private static final String FILTER_PANEL = "//div[contains(@class, 'filterPageToolbar__filter-page-toolbar')]";
  private static final String SEARCH_INPUT = ".//input[@type='text']";

  private SelenideElement getFilterComponent() {
    return $x(FILTER_PANEL).shouldBe(visible);
  }

  public void filtersPageShouldBeOpened() {
    $x(PAGE_TITLE_XPATH).shouldBe(visible, ofMillis(TEN_SEC.getValue()));
    isPageLoaded();
    log.info("Filters page is opened");
  }

  public void applySearchFilterByName(final String filterName) {
    getFilterComponent().$x(SEARCH_INPUT).sendKeys(filterName);
    log.info("Search filters by name: %s".formatted(filterName));
  }

}
