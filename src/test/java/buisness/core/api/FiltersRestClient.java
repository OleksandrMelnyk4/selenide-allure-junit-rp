package buisness.core.api;

import core.dto.api.FilterDto;
import core.enums.TestCacheKey;
import core.utils.TestCache;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static core.enums.PropertyNames.BASE_URL;
import static core.utils.services.ConfigurationService.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.util.Objects.requireNonNull;

@Slf4j
public class FiltersRestClient extends ApiRestClient {
  private final static String PROJECT_ID = "projectId";
  private final static String FILTER_ID = "filterId";
  private final RequestSpecification REQS_PEC = given()
    .when().baseUri(getProperty(BASE_URL.getName()))
    .accept(JSON)
    .contentType(JSON)
    .auth().oauth2(getBearerToken());


  public void getAllFilters(final String path) {
    String project = TestCache.get(TestCacheKey.PROJECT, String.class);
    ValidatableResponse response = given()
      .spec(REQS_PEC)
      .basePath(path).pathParam(PROJECT_ID, project)
      .get()
      .then().log().all();

    TestCache.putDataInCache(TestCacheKey.RESPONSE, response);
  }

  public void getFilterById(final String path) {
    String project = requireNonNull(TestCache.get(TestCacheKey.PROJECT, String.class));
    Integer filterId = requireNonNull(TestCache.get(TestCacheKey.FILTER_ID, Integer.class));
    ValidatableResponse response = given()
      .spec(REQS_PEC)
      .basePath(path).pathParams(PROJECT_ID, project, FILTER_ID, filterId)
      .get()
      .then().log().all();

    TestCache.putDataInCache(TestCacheKey.RESPONSE, response);
  }

  public void createNewFilter(final String path, final FilterDto newFilter) {
    String project = requireNonNull(TestCache.get(TestCacheKey.PROJECT, String.class));

    ValidatableResponse response = given()
      .spec(REQS_PEC)
      .basePath(path).pathParam(PROJECT_ID, project)
      .body(newFilter)
      .post()
      .then().log().all();

    TestCache.putDataInCache(TestCacheKey.RESPONSE, response);
  }

  public void updateExistingFilter(final String path, final FilterDto newFilter) {
    String project = requireNonNull(TestCache.get(TestCacheKey.PROJECT, String.class));
    Integer filterId = requireNonNull(TestCache.get(TestCacheKey.FILTER_ID, Integer.class));

    ValidatableResponse response = given()
      .spec(REQS_PEC)
      .basePath(path).pathParams(PROJECT_ID, project, FILTER_ID, filterId)
      .body(newFilter)
      .put()
      .then().log().all();

    TestCache.putDataInCache(TestCacheKey.RESPONSE, response);
  }

  public void deleteExistingFilter(final String path) {
    String project = requireNonNull(TestCache.get(TestCacheKey.PROJECT, String.class));
    Integer filterId = requireNonNull(TestCache.get(TestCacheKey.FILTER_ID, Integer.class));

    ValidatableResponse response = given()
      .spec(REQS_PEC)
      .basePath(path).pathParams(PROJECT_ID, project, FILTER_ID, filterId)
      .delete()
      .then().log().all();

    TestCache.putDataInCache(TestCacheKey.RESPONSE, response);
  }
}
