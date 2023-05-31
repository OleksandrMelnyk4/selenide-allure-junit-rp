package buisness.core.api;

import core.dto.api.FilterDto;
import core.enums.TestCacheKey;
import core.utils.TestCache;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static core.enums.PropertyNames.BASE_URL;
import static core.utils.services.ConfigurationService.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.util.Objects.requireNonNull;

@Slf4j
public class FiltersRestClient extends ApiRestClient {

  private static final String FILTERS_PATH = "api/v1/%s/filter";

  private static String getUrlForUpdate() {
    String project = requireNonNull(TestCache.get(TestCacheKey.PROJECT, String.class));
    Integer filterId = requireNonNull(TestCache.get(TestCacheKey.FILTER_ID, Integer.class));
    return getProperty(BASE_URL.getName()) + FILTERS_PATH.formatted(project) + "/" + filterId;
  }

  public void getAllFilters() {
    String project = TestCache.get(TestCacheKey.PROJECT, String.class);
    String url = getProperty(BASE_URL.getName()) + FILTERS_PATH.formatted(project);
    ValidatableResponse response = given()
      .when()
      .accept(JSON)
      .contentType(JSON)
      .auth().oauth2(getBearerToken())
      .get(url)
      .then().log().all();

    TestCache.putDataInCache(TestCacheKey.RESPONSE, response);
  }

  public void getFilterById() {
    String project = requireNonNull(TestCache.get(TestCacheKey.PROJECT, String.class));
    Integer filterId = requireNonNull(TestCache.get(TestCacheKey.FILTER_ID, Integer.class));
    String url = getUrlForUpdate(project, filterId);

    ValidatableResponse response = given()
      .when()
      .accept(JSON)
      .contentType(JSON)
      .auth().oauth2(getBearerToken())
      .get(url)
      .then().log().all();

    TestCache.putDataInCache(TestCacheKey.RESPONSE, response);
  }

  private String getUrlForUpdate(String project, Integer filterId) {
    return getProperty(BASE_URL.getName()) + FILTERS_PATH.formatted(project) + "/" + filterId;
  }

  public void createNewFilter(final FilterDto newFilter) {
    String project = requireNonNull(TestCache.get(TestCacheKey.PROJECT, String.class));
    String url = getProperty(BASE_URL.getName()) + FILTERS_PATH.formatted(project);

    ValidatableResponse response = given()
      .when()
      .accept(JSON)
      .contentType(JSON)
      .auth().oauth2(getBearerToken())
      .body(newFilter)
      .post(url)
      .then().log().all();

    TestCache.putDataInCache(TestCacheKey.RESPONSE, response);
  }

  public void updateExistingFilter(final FilterDto newFilter) {
    ValidatableResponse response = given()
      .when()
      .accept(JSON)
      .contentType(JSON)
      .auth().oauth2(getBearerToken())
      .body(newFilter)
      .put(getUrlForUpdate())
      .then().log().all();

    TestCache.putDataInCache(TestCacheKey.RESPONSE, response);
  }

  public void deleteExistingFilter() {

    ValidatableResponse response = given()
      .when()
      .accept(JSON)
      .contentType(JSON)
      .auth().oauth2(getBearerToken())
      .delete(getUrlForUpdate())
      .then().log().all();

    TestCache.putDataInCache(TestCacheKey.RESPONSE, response);
  }
}
