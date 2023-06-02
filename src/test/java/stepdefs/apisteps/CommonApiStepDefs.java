package stepdefs.apisteps;

import core.enums.TestCacheKey;
import core.enums.UsersRole;
import core.utils.TestCache;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static core.enums.TestCacheKey.RESPONSE;
import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertNotNull;

@Slf4j
public class CommonApiStepDefs {

  @Given("User with role {} and {} project")
  public void dashboardPageIsOpened(final UsersRole userRole, final String project) {
    TestCache.putDataInCache(TestCacheKey.USER, userRole);
    TestCache.putDataInCache(TestCacheKey.PROJECT, project);
  }

  @Then("Verify response has {int} status code")
  public void verifyResponseHasStatusCode(final int code) {
    ValidatableResponse response = TestCache.get(RESPONSE, ValidatableResponse.class);
    requireNonNull(response).statusCode(code);
  }

  @And("Verify response body is not empty")
  public void verifyResponseBodyIsNotEmpty() {
    ValidatableResponse response = requireNonNull(TestCache.get(RESPONSE, ValidatableResponse.class));
    assertNotNull(response.extract().body());
  }
}
