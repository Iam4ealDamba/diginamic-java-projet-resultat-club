package fr.iamdamba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.services.interfaces.Dao;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ApparitionDao implements Dao<Apparition> {
  /** Liste des apparitions */
  public List<Apparition> listItems;

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
  public ApparitionDao(EntityManager manager, Logger logger) {
    this.manager = manager;
    this.logger = logger;
    this.listItems = this.all() == null ? new ArrayList<>() : this.all();
  }

  @Override
  public List<Apparition> all() {
    List<Apparition> query = manager.createQuery("SELECT t FROM Apparition t", Apparition.class).getResultList();
    if (query.stream().count() == 0) {
      return null;
    }
    return query;
  }

  @Override
  public Optional<Apparition> one(Object id) {
    Apparition query = manager.find(Apparition.class, id);

    return Optional.ofNullable(query);
  }

  @Override
  public void create(Apparition t) {
    try {
      manager.persist(t);
      this.listItems.add(t);
    } catch (EntityExistsException e) {
      logger.error("Impossible de creer l'apparition", e);
    }
  }

  @Override
  public void update(Apparition old, Apparition update) {
    if (old.equals(update)) {
      logger.debug("Apparition déjà à jour");
    } else {
      try {
        manager.merge(update);
        this.listItems.set(this.listItems.indexOf(old), update);
      } catch (IllegalArgumentException e) {
        logger.error("Impossible de mettre à jour l'apparition", e);
      }
    }
  }

  @Override
  public void delete(Apparition t) {
    try {
      manager.remove(t);
      this.listItems.remove(t);
    } catch (IllegalArgumentException e) {
      logger.error("Impossible de supprimer l'apparition", e);
    }
  }

}
