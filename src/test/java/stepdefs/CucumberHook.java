package stepdefs;

import buisness.core.api.FiltersRestClient;
import com.codeborne.selenide.Selenide;
import core.enums.TestCacheKey;
import core.utils.TestCache;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;

import java.util.Base64;
import java.util.Objects;

import static core.utils.TestCache.initializeTestCache;

@Slf4j
public class CucumberHook {

  private final FiltersRestClient filtersRestClient = new FiltersRestClient();

  @Before("@Api")
  public void preSetUp() {
    initializeTestCache();
  }

  @After("@Ui")
  public void afterScenario(Scenario scenario) {
    log.info("FINISH SCENARIO: " + scenario.getName());
    Selenide.clearBrowserCookies();
    Selenide.closeWindow();
    Selenide.closeWebDriver();
  }

  @After("@Ui")
  public void takeScreenshot(Scenario scenario) {
    if (scenario.isFailed()) {
      String screenshotAsBase64 = Selenide.screenshot(OutputType.BASE64);
      byte[] decoded = Base64.getDecoder().decode(screenshotAsBase64);
      scenario.attach(decoded, "image/png", "screenshot");
    }
  }

  @After("@filter_is_great")
  public void describingFiltersPage() {
    log.info("As we mentioned, our Filters page look awesome!");
  }

  @After("@delete_filter_if_exist")
  public void deleteFilter() {
    String project = TestCache.get(TestCacheKey.PROJECT, String.class);
    Integer filterId = TestCache.get(TestCacheKey.FILTER_ID, Integer.class);
    if (Objects.isNull(project) && Objects.isNull(filterId)) {
      filtersRestClient.deleteExistingFilter();
    }
  }
}
