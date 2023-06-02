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

  @When("User sends GET filters request to {}")
  public void userSendsGetFiltersRequest(final String path) {
    filtersRestClient.getAllFilters(path);
  }

  @When("User sends GET filter by ID request to {}")
  public void userSendsGetFilterByIdRequest(final String path) {
    filtersRestClient.getFilterById(path);
  }

  @When("User sends DELETE filters request to {}")
  public void userSendsDeleteFiltersRequest(final String path) {
    filtersRestClient.deleteExistingFilter(path);
  }

  @And("Verify response body contains filters")
  public void verifyResponseBodyContainsFilters() {
    ValidatableResponse response = TestCache.get(RESPONSE, ValidatableResponse.class);
    GetFiltersResponseDto filtersResponse = requireNonNull(response).extract().as(GetFiltersResponseDto.class);
    assertNotEquals(filtersResponse.getPage().getTotalElements(), 0);
  }

  @When("User sends POST filter request to {}")
  public void userSendsPostFilterRequest(final String path) {
    FilterDto newFilter = buildNewFilter();
    filtersRestClient.createNewFilter(path, newFilter);
  }

  @When("Save new filter ID")
  public void saveNewFilterId() {
    ValidatableResponse response = requireNonNull(TestCache.get(RESPONSE, ValidatableResponse.class));
    TestCache.putDataInCache(FILTER_ID, requireNonNull(response.extract().body().path("id")));
  }

  @When("User sends POST filter request request with wrong body to {}")
  public void userSendsPostFilterRequestWithWrongBody(final String path) {
    filtersRestClient.createNewFilter(path, FilterDto.builder()
      .description("some description")
      .build());
  }

  @When("User updates new {string} description for existing filter to {}")
  public void updateDescriptionForExistingFilter(final String description, final String path) {
    ValidatableResponse response = requireNonNull(TestCache.get(RESPONSE, ValidatableResponse.class));
    FilterDto filter = response.extract().as(FilterDto.class);
    filter.setDescription(description);
    filtersRestClient.updateExistingFilter(path, filter);
  }

  @When("User updates existing filter with no conditions to {}")
  public void updateExistingFilterWithoutConditions(final String path) {
    ValidatableResponse response = requireNonNull(TestCache.get(RESPONSE, ValidatableResponse.class));
    FilterDto filter = response.extract().as(FilterDto.class);
    filter.setConditions(null);
    filtersRestClient.updateExistingFilter(path, filter);
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
