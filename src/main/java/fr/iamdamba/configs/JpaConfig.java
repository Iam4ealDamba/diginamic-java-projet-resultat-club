package fr.iamdamba.configs;

import org.slf4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaConfig {
  /** Unite de persistance */
  private EntityManagerFactory factory;

  /** Gestionnaire de transaction */
  private EntityManager manager;

  /** Transaction en cours */
  private EntityTransaction transaction;

  /** Logger */
  public static Logger logger;

  /**
   * Constructeur
   * 
   * @param unitName - Nom de l'unite de persistance
   */
  public JpaConfig(String unitName, Logger logger) {
    this.factory = Persistence.createEntityManagerFactory(unitName);
    this.manager = factory.createEntityManager();
    this.transaction = this.manager.getTransaction();
    this.logger = logger;
  }

  /** Demarre une transaction */
  public void startTransaction() {
    logger.info("Demarrage d'une transaction");
    this.transaction.begin();
  }

  /** Termine une transaction et commit */
  public void commitTransaction() {
    this.transaction.commit();
    logger.info("Commit de la transaction");
  }

  /** Ferme l'unite de persistance */
  public void close() {
    this.factory.close();
    this.manager.close();
    logger.info("Fermeture de l'unite de persistance");
  }

  /** Retourne le gestionnaire de transaction */
  public EntityManager getManager() {
    return this.manager;
  }

}
