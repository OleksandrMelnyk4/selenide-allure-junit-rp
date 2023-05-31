package core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestCacheKey {

  RESPONSE("RESPONSE"),
  FILTER_ID("FILTER_ID"),
  USER("USER"),
  PROJECT("PROJECT");

  private final String key;
}
