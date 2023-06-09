package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.utils.Timeout.FIVE_SEC;
import static core.utils.Timeout.TWO_SEC;
import static java.util.concurrent.TimeUnit.SECONDS;

public class LoginPage extends PageFactory {
  public static final String SUBMIT_BUTTON_XPATH = "//button[@type='submit']";
  private final WebDriver driver;

  @FindBy(how = How.XPATH, using = "//input[@name='login']")
  public WebElement loginField;

  @FindBy(how = How.XPATH, using = "//input[@name='password']")
  public WebElement passwordField;


  public LoginPage(WebDriver driver) {
    this.driver = driver;
    initElements(driver, this);
  }

  private void setLogin(final String login) {
    loginField.sendKeys(login);
  }

  private void setPassword(final String password) {
    passwordField.sendKeys(password);
  }

  private void clickLoginButton() {
    clickOnButton(By.xpath(SUBMIT_BUTTON_XPATH));
  }

  public void loginToReportPortal(final String login, final String password) {
    setLogin(login);
    setPassword(password);
    clickLoginButton();
    driver.manage().timeouts().implicitlyWait(FIVE_SEC.getValue(), SECONDS);
  }

  private void clickOnButton(final By locator) {
    WebElement button;
    new WebDriverWait(driver, TWO_SEC.getValue()).
      until(ExpectedConditions.elementToBeClickable(locator));
    button = driver.findElement(locator);
    button.click();
  }
}
