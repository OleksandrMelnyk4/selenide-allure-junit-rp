package core.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  plugin = {
    "pretty",
//    "io.qameta.allure.cucumber6jvm.allureCucumber6jvm",
    "html:target/cucumber-reports/Cucumber-Html.html",
    "json:target/cucumber-reports/Cucumber.json"
  },
  features = {"src/test/resources/features/"},
  glue = {"stepdefs"},
  tags = "@smoke",
  stepNotifications = true)
public class RunCucumberTest {
}
