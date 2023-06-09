package tests.utils;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;

public class TakeScreenShotOnFailure implements TestWatcher {
  WebDriver driver;

  public TakeScreenShotOnFailure(WebDriver driver) {
    this.driver = driver;
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable throwable) {
    captureScreenshot(driver, context.getDisplayName());
  }

  @SneakyThrows
  public void captureScreenshot(WebDriver driver, String fileName) {
    String path = "./target/screenshots/";
    new File(path).mkdirs();
    try (FileOutputStream out = new FileOutputStream(path +
      File.separator + "screenshot-" + fileName + ".png")) {
      out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
    }
  }

}
