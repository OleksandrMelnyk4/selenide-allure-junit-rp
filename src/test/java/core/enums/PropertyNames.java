package core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PropertyNames {
  BASE_URL("base.uri"),
  BROWSER("configuration.browser"),
  DRIVER_MANAGER_ENABLED("configuration.driverManagerEnabled"),
  BROWSER_SIZE("configuration.browserSize"),
  HEADLESS("configuration.headless"),
  WEB_DRIVER_LOGS_ENABLED("configuration.webdriverLogsEnabled"),
  AUTHORISATION_NAME("authorisation.user.name"),
  AUTHORISATION_PASSWORD("authorisation.user.password"),
  AUTHORISATION_URL("authorisation.url"),
  DEFAULT("default");

  private final String name;
}
