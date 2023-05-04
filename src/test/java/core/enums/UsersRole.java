package core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsersRole {
  ADMINISTRATOR("admin"),
  DEFAULT("default");

  private final String name;
}
