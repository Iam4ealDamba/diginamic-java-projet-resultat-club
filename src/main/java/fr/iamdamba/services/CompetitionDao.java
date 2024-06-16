package fr.iamdamba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.Competition;
import fr.iamdamba.services.interfaces.Dao;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;

public class CompetitionDao implements Dao<Competition> {
  /** Liste des apparitions */
  public List<Competition> listItems;

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
  public CompetitionDao(EntityManager manager, Logger logger) {
    this.manager = manager;
    this.logger = logger;

    this.listItems = this.all() == null ? new ArrayList<>() : this.all();
  }

  @Override
  public List<Competition> all() {
    List<Competition> query = manager.createQuery("SELECT t FROM Competition t", Competition.class).getResultList();
    if (query.stream().count() == 0) {
      return null;
    }
    return query;
  }

  @Override
  public Optional<Competition> one(Object id) {
    Competition query = manager.find(Competition.class, id);

    return Optional.ofNullable(query);
  }

  @Override
  public void create(Competition t) {
    manager.persist(t);
    this.listItems.add(t);
  }

  @Override
  public void update(Competition old, Competition update) {
    if (old.equals(update)) {
      logger.debug("Competition déjà à jour");
    } else {
      try {
        manager.merge(update);
        this.listItems.set(this.listItems.indexOf(old), update);
      } catch (IllegalArgumentException e) {
        logger.error("Impossible de mettre à jour le Competition", e);
      }
    }
  }

  @Override
  public void delete(Competition t) {
    try {
      manager.remove(t);
      this.listItems.remove(t);
    } catch (IllegalArgumentException e) {
      logger.error("Impossible de supprimer le Competition", e);
    }
  }

}
