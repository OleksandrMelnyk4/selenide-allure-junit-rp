package buisness.core;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static core.utils.Timeout.TEN_SEC;
import static java.time.Duration.ofMillis;

@Slf4j
public class FiltersPage extends BasePage {
  private static final String PAGE_TITLE_XPATH = ".//span[@title = 'Filters']";
  private static final String FILTER_PANEL = "//div[contains(@class, 'filterPageToolbar__filter-page-toolbar')]";
  private static final String SEARCH_INPUT = ".//input[@type='text']";
  private static final String FILTER_ITEM_LINE = ".//div[contains(@class, 'gridRow__grid-row-wrapper')]";
  private static final String FILTER_NAME_TEMPLATE = "//span[text()='%s']";
  private static final String FILTER_NAME = "//span[contains(@class, 'filterName__name-wrapper')]";
  private static final String GRID_COLUMN_NAME_TEMPLATE = "//span[contains(@class, 'headerCell__title-full') and text()='%s']";


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

  public void gridShouldContainsFilters() {
    $$x(FILTER_ITEM_LINE).shouldHave(sizeGreaterThan(0));
  }

  public void openFilterByName(final String filterName) {
    $x(FILTER_ITEM_LINE + FILTER_NAME_TEMPLATE.formatted(filterName))
      .shouldBe(visible).click();
  }

  public void mainGridContainsColumns(final String columnName) {
    $x(GRID_COLUMN_NAME_TEMPLATE.formatted(columnName)).shouldBe(visible);
    log.info("Column name is present %s".formatted(columnName));
  }

  public void openAnyFilter() {
    $x(FILTER_NAME).shouldBe(visible).click();
  }
}
