package buisness.core.api;

import core.dto.LoginUserDto;
import core.enums.TestCacheKey;
import core.enums.UsersRole;
import core.utils.TestCache;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

import static buisness.service.LoginService.getUser;
import static core.enums.PropertyNames.AUTHORISATION_NAME;
import static core.enums.PropertyNames.AUTHORISATION_PASSWORD;
import static core.enums.PropertyNames.AUTHORISATION_URL;
import static core.enums.PropertyNames.BASE_URL;
import static core.utils.services.ConfigurationService.getProperty;
import static java.lang.ThreadLocal.withInitial;
import static java.util.Objects.requireNonNull;

@Slf4j
public abstract class ApiRestClient {
  private final ThreadLocal<String> token = withInitial(() -> null);


  public String getBearerToken() {
    if (null == token.get()) {
      token.set(requireNonNull(generateBearerToken()));
    }
    return token.get();
  }

  private String generateBearerToken() {
    UsersRole userRole = TestCache.get(TestCacheKey.USER, UsersRole.class);
    LoginUserDto user = getUser(requireNonNull(userRole).getName());
    return RestAssured.given()
      .baseUri(getProperty(BASE_URL.getName()))
      .contentType(ContentType.URLENC)
      .auth().basic(getProperty(AUTHORISATION_NAME.getName()), getProperty(AUTHORISATION_PASSWORD.getName()))
      .formParam("grant_type", "password")
      .formParam("username", user.getUserName())
      .formParam("password", user.getUserPassword())
      .post(getProperty(AUTHORISATION_URL.getName()))
      .then()
      .extract()
      .path("access_token");
  }
}
