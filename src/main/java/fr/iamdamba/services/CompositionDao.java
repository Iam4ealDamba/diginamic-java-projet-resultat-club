package fr.iamdamba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.Composition;
import fr.iamdamba.services.interfaces.Dao;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;

public class CompositionDao implements Dao<Composition> {
  /** Liste des apparitions */
  public List<Composition> listItems;

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
  public CompositionDao(EntityManager manager, Logger logger) {
    this.manager = manager;
    this.logger = logger;

    this.listItems = this.all() == null ? new ArrayList<>() : this.all();
  }

  @Override
  public List<Composition> all() {
    List<Composition> query = manager.createQuery("SELECT t FROM Composition t", Composition.class).getResultList();
    if (query.stream().count() == 0) {
      return null;
    }
    return query;
  }

  @Override
  public Optional<Composition> one(Object id) {
    Composition query = manager.find(Composition.class, id);

    return Optional.ofNullable(query);
  }

  @Override
  public void create(Composition t) {
    try {
      manager.persist(t);
      this.listItems.add(t);
    } catch (EntityExistsException e) {
      logger.error("Impossible de creer le Composition", e);
    }
  }

  @Override
  public void update(Composition old, Composition update) {
    if (old.equals(update)) {
      logger.debug("Composition déjà à jour");
    } else {
      try {
        manager.merge(update);
        this.listItems.set(this.listItems.indexOf(old), update);
      } catch (IllegalArgumentException e) {
        logger.error("Impossible de mettre à jour le Composition", e);
      }
    }
  }

  @Override
  public void delete(Composition t) {
    try {
      manager.remove(t);
      this.listItems.remove(t);
    } catch (IllegalArgumentException e) {
      logger.error("Impossible de supprimer le Composition", e);
    }
  }

}
