package utils.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {


  Config config = readConfig();
  String URL = readConfig().getString("url");

  static Config readConfig() {
    return ConfigFactory.load("application.conf");
  }

  static String getLogin(String userRole) {
    return readConfig().getString(String.format("usersParams.%s.login", userRole));
  }

  static String getPassword(String userRole) {
    return readConfig().getString(String.format("usersParams.%s.password", userRole));
  }
}
