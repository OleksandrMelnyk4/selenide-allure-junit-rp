package core.utils.services;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class ConfigurationService {

  private static final ThreadLocal<ConfigurationService> instance = new ThreadLocal<>();
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
}
