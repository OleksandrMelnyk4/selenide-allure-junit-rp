package core.utils.services;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.SneakyThrows;

import java.util.List;

public class DataDrivenProvider {
  @SneakyThrows
  public static List<String> readResource(final String file) {
    return List.of(Resources.toString(Resources.getResource(file), Charsets.UTF_8)
      .split(",".strip()));
  }
}
