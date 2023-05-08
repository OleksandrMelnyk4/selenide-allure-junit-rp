package buisness.core;

import com.codeborne.selenide.Selenide;

import static core.utils.services.ConfigurationService.getProperty;

public class HomePage {

  public void openHomePage() {
    Selenide.open(getProperty("base.uri"));
    Selenide.webdriver().driver().getWebDriver().manage().window().maximize();
  }

}
