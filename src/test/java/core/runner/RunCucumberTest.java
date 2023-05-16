package core.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  plugin = {
    "pretty",
    "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
    "html:build/cucumber-report.html",
  },
  features = {"src/test/resources/features/"},
  glue = {"stepdefs"},
  tags = "@smoke",
  stepNotifications = true)
public class RunCucumberTest {
}
