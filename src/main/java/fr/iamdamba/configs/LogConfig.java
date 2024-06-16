package fr.iamdamba.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogConfig {
  public Logger logger;

  public LogConfig(Class<?> clazz) {
    this.logger = LoggerFactory.getLogger(clazz);
  }

  public Logger getLogger() {
    return this.logger;
  }
}
