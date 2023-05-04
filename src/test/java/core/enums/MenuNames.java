package core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuNames {
  DASHBOARD("dashboard"),
  LAUNCHES("launches"),
  FILTERS("filters"),
  PROJECT_SETTINGS("settings");

  private final String name;

  @Override
  public String toString() {
    return name;
  }
}
