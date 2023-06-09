package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static core.utils.Timeout.TWO_SEC;
import static org.junit.Assert.assertTrue;

public class DashboardPage extends PageFactory {
  public static final String TITLE_ALL_DASHBOARDS_XPATH = "//span[@title = 'All Dashboards']";
  public static final String DEMO_DASHBOARD_LINE_XPATH = "//div[contains(@class, 'gridRow')]//a[text() = 'DEMO DASHBOARD']";
  public static final String WIDGETS_GRID_XPATH = "//div[contains(@class, 'react-grid-item widgetsGrid')]";
  public static final String RESIZE_WIDGETS_BUTTON_XPATH = "//span[@class ='react-resizable-handle react-resizable-handle-se']";
  public static final String WIDGET_XPATH_TEMPLATE = "//div[contains(@class, 'react-grid-item widgetsGrid') and contains(., '%s')]";
  private final WebDriver driver;

  public DashboardPage(WebDriver driver) {
    this.driver = driver;
    initElements(driver, this);
  }

  public void waitTillPageLoaded() {
    elementVisibility(By.xpath(TITLE_ALL_DASHBOARDS_XPATH));
  }

  public void navigateToDashboardWidgets() {
    WebElement element = driver.findElement(By.xpath(DEMO_DASHBOARD_LINE_XPATH));
    clickOnElementUsingJs(element);

    elementVisibility(By.xpath(WIDGETS_GRID_XPATH));
  }

  public void scrollToTheWidget(final String widget) {

    Actions action = new Actions(driver);
    boolean state = isWidgetElementIsVisible(widget);
    while (state) {
      //change focus and star scrolling
      driver.findElement(By.xpath(WIDGETS_GRID_XPATH)).click();
      action.sendKeys(Keys.PAGE_DOWN).build().perform();
      state = isWidgetElementIsVisible(widget);
    }

    WebElement searchedWidget = driver.findElement(By.xpath(WIDGET_XPATH_TEMPLATE.formatted(widget)));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchedWidget);
  }

  public void isWidgetVisible(final String widget) {
    elementVisibility(By.xpath(WIDGET_XPATH_TEMPLATE.formatted(widget)));
  }

  public void navigateToTheWidgetVisible(final String widget) {
    clickOnElementUsingJs(driver.findElement(By.xpath(WIDGET_XPATH_TEMPLATE.formatted(widget))));
  }

  private boolean isWidgetElementIsVisible(final String widgetName) {
    List<WebElement> widgets = driver.findElements(By.xpath(WIDGETS_GRID_XPATH));
    return widgets.stream().noneMatch(el -> el.getText().contains(widgetName));
  }

  public void resizeWidget(final String widgetName) {
    String widget = WIDGET_XPATH_TEMPLATE.formatted(widgetName);

    WebElement widgetToResize = driver.findElement(By.xpath(widget));
    Dimension oldElementSize = widgetToResize.getSize();

    WebElement scrollOfElement = driver.findElement(By.xpath(widget + RESIZE_WIDGETS_BUTTON_XPATH));

    resizeElement(scrollOfElement, true);
    Dimension newElementSize = widgetToResize.getSize();

    verifyDimensionChanges(oldElementSize, newElementSize);
    resizeElement(scrollOfElement, false);
  }

  private void clickOnElementUsingJs(final WebElement element) {
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].click();", element);
  }

  private void resizeElement(WebElement scrollOfElement, final boolean isResize) {
    Actions action = new Actions(driver);
    int x;
    int y;

    if (isResize) {
      x = 200;
      y = 200;
    } else {
      x = -200;
      y = -200;
    }

    action
      .clickAndHold(scrollOfElement)
      .moveByOffset(x, y)
      .perform();
    action.release(scrollOfElement).perform();
  }

  private void verifyDimensionChanges(final Dimension oldSize, final Dimension newSize) {
    assertTrue("Dimension was not resized",
      newSize.height > oldSize.height && newSize.width > oldSize.width);
  }

  private void elementVisibility(final By locator) {
    new WebDriverWait(driver, TWO_SEC.getValue()).
      until(ExpectedConditions.visibilityOfElementLocated(locator));
  }
}

