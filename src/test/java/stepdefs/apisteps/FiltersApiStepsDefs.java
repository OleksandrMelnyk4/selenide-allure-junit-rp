package stepdefs.apisteps;

import buisness.core.api.FiltersRestClient;
import core.dto.api.FilterDto;
import core.dto.api.GetFiltersResponseDto;
import core.utils.TestCache;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

import static core.enums.TestCacheKey.FILTER_ID;
import static core.enums.TestCacheKey.RESPONSE;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Slf4j
public class FiltersApiStepsDefs {

  private final FiltersRestClient filtersRestClient = new FiltersRestClient();

  private static FilterDto buildNewFilter() {
    return FilterDto.builder()
      .conditions(Collections.singletonList(new FilterDto.Condition("has", "compositeAttribute", randomAlphabetic(5))))
      .description(randomAlphabetic(10))
      .name(randomAlphabetic(10))
      .orders(Collections.singletonList(new FilterDto.Order(true, "startTime")))
      .share(false)
      .type("Launch")
      .build();
  }

  @When("User sends GET filters request")
  public void userSendsGetFiltersRequest() {
    filtersRestClient.getAllFilters();
  }

  @When("User sends GET filter by ID request")
  public void userSendsGetFilterByIdRequest() {
    filtersRestClient.getFilterById();
  }

  @When("User sends DELETE filters request")
  public void userSendsDeleteFiltersRequest() {
    filtersRestClient.deleteExistingFilter();
  }

  @And("Verify response body contains filters")
  public void verifyResponseBodyContainsFilters() {
    ValidatableResponse response = TestCache.get(RESPONSE, ValidatableResponse.class);
    GetFiltersResponseDto filtersResponse = requireNonNull(response).extract().as(GetFiltersResponseDto.class);
    assertNotEquals(filtersResponse.getPage().getTotalElements(), 0);
  }

  @When("User sends POST filter request")
  public void userSendsPostFilterRequest() {
    FilterDto newFilter = buildNewFilter();
    filtersRestClient.createNewFilter(newFilter);
  }

  @When("Save new filter ID")
  public void saveNewFilterId() {
    ValidatableResponse response = requireNonNull(TestCache.get(RESPONSE, ValidatableResponse.class));
    TestCache.putDataInCache(FILTER_ID, requireNonNull(response.extract().body().path("id")));
  }

  @When("User sends POST filter request request with wrong body")
  public void userSendsPostFilterRequestWithWrongBody() {
    filtersRestClient.createNewFilter(FilterDto.builder()
      .description("some description")
      .build());
  }

  @When("User updates new {string} description for existing filter")
  public void updateDescriptionForExistingFilter(final String description) {
    ValidatableResponse response = requireNonNull(TestCache.get(RESPONSE, ValidatableResponse.class));
    FilterDto filter = response.extract().as(FilterDto.class);
    filter.setDescription(description);
    filtersRestClient.updateExistingFilter(filter);
  }

  @When("User updates existing filter with no conditions")
  public void updateExistingFilterWithoutConditions() {
    ValidatableResponse response = requireNonNull(TestCache.get(RESPONSE, ValidatableResponse.class));
    FilterDto filter = response.extract().as(FilterDto.class);
    filter.setConditions(null);
    filtersRestClient.updateExistingFilter(filter);
  }

  @Then("Verify that filter has {string} description")
  public void verifyThatFilterHasUpdatedDescriptionForTheFilterDescription(final String description) {
    ValidatableResponse response = requireNonNull(TestCache.get(RESPONSE, ValidatableResponse.class));
    FilterDto filter = response.extract().as(FilterDto.class);
    assertEquals(description, filter.getDescription());
  }

  @And("Set not existing Filter ID")
  public void setNotExistingFilterID() {
    TestCache.putDataInCache(FILTER_ID, 99999);
  }
}
