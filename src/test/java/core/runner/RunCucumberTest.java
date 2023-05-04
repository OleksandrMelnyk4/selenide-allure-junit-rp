package core.runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("core/runner")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "core.runner")
@CucumberOptions(
  plugin = {
    "pretty",
    "io.qameta.allure.cucumber7jvm.allureCucumber7jvm",
    "html:target/cucumber-reports/Cucumber-Html.html",
    "json:target/cucumber-reports/Cucumber.json",
    "junit:target/cucumber-reports/Cucumber.xml"},
  features = {"src/test/resources/features/"},
  glue = {"stepdefs"})
public class RunCucumberTest {
}
