package fr.iamdamba.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogConfig {
  /** Logger de l'application */
  public Logger logger;

  /** Constructeur */
  public LogConfig(Class<?> clazz) {
    this.logger = LoggerFactory.getLogger(clazz);
  }

  /** Retourne le logger de l'application */
  public Logger getLogger() {
    return this.logger;
  }
}
