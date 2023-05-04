package core.utils.services;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import core.dto.LoginUserDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class ConfigurationService {

  private static final Object SYN_OBJ = new Object();
  private static final ThreadLocal<ConfigurationService> instance = new ThreadLocal<>();
  private static final ThreadLocal<Configuration> configuration = new ThreadLocal<>();
  private final Properties properties = new Properties();

  private ConfigurationService() {
    try {
      loadProperties("common");
    } catch (final IOException e) {
      throw new IllegalStateException("Failed to load platform configuration file", e);
    }
  }

  public static String getProperty(final String propertyName) {
    if (Objects.isNull(instance.get())) {
      instance.set(new ConfigurationService());
    }
    return System.getProperty(propertyName, instance.get().properties.getProperty(propertyName));
  }

  private void loadProperties(final String resource) throws IOException {
    log.info(String.format("Reading environment properties: %s.properties", resource));
    InputStream inputStream = getClass().getClassLoader()
      .getResourceAsStream(String.format("%s.properties", resource));
    if (Objects.isNull(inputStream)) {
      throw new IOException("Unable to open stream for resource " + resource);
    }
    final Properties props = new Properties();
    props.load(inputStream);
    inputStream.close();
    for (final String propertyName : props.stringPropertyNames()) {
      if (propertyName.startsWith("+")) {
        loadProperties(propertyName.substring(1));
      }
    }
    properties.putAll(props);
  }

  public static LoginUserDto getUser(String userRole) {
    return new LoginUserDto(getProperty("usersParams.%s.login".formatted(userRole)), getProperty("usersParams.%s.password".formatted(userRole)));
  }
}
