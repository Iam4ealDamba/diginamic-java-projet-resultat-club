package fr.iamdamba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.Evenement;
import fr.iamdamba.services.interfaces.Dao;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;

public class EvenementDao implements Dao<Evenement> {
  /** Liste des apparitions */
  public List<Evenement> listItems;

  /** Gestionnaire d'entité */
  private EntityManager manager;

  /** Logger */
  private Logger logger;

  /**
   * Constructeur
   * 
   * @param manager Gestionnaire d'entité
   * @param logger
   */
  public EvenementDao(EntityManager manager, Logger logger) {
    this.manager = manager;
    this.logger = logger;

    this.listItems = this.all() == null ? new ArrayList<>() : this.all();
  }

  @Override
  public List<Evenement> all() {
    List<Evenement> query = manager.createQuery("SELECT t FROM Evenement t", Evenement.class).getResultList();
    if (query.stream().count() == 0) {
      return null;
    }
    return query;
  }

  @Override
  public Optional<Evenement> one(Object id) {
    Evenement query = manager.find(Evenement.class, id);

    return Optional.ofNullable(query);
  }

  @Override
  public void create(Evenement t) {
    try {
      manager.persist(t);
      this.listItems.add(t);
    } catch (EntityExistsException e) {
      logger.error("Impossible de creer le Evenement", e);
    }
  }

  @Override
  public void update(Evenement old, Evenement update) {
    if (old.equals(update)) {
      logger.debug("Evenement déjà à jour");
    } else {
      try {
        manager.merge(update);
        this.listItems.set(this.listItems.indexOf(old), update);
      } catch (IllegalArgumentException e) {
        logger.error("Impossible de mettre à jour le Evenement", e);
      }
    }
  }

  @Override
  public void delete(Evenement t) {
    try {
      manager.remove(t);
      this.listItems.remove(t);
    } catch (IllegalArgumentException e) {
      logger.error("Impossible de supprimer le Evenement", e);
    }
  }

}
