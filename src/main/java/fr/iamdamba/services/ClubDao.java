package fr.iamdamba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.Club;
import fr.iamdamba.entities.Club;
import fr.iamdamba.services.interfaces.Dao;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;

public class ClubDao implements Dao<Club> {
  /** Liste des apparitions */
  public List<Club> listItems;

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
  public ClubDao(EntityManager manager, Logger logger) {
    this.manager = manager;
    this.logger = logger;

    this.listItems = this.all() == null ? new ArrayList<>() : this.all();
  }

  @Override
  public List<Club> all() {
    List<Club> query = manager.createQuery("SELECT t FROM Club t", Club.class).getResultList();
    if (query.stream().count() == 0) {
      return null;
    }
    return query;
  }

  @Override
  public Optional<Club> one(Object id) {
    Club query = manager.find(Club.class, id);

    return Optional.ofNullable(query);
  }

  @Override
  public void create(Club t) {
    try {
      manager.persist(t);
      this.listItems.add(t);
    } catch (EntityExistsException e) {
      logger.error("Impossible de creer le club", e);
    }
  }

  @Override
  public void update(Club old, Club update) {
    if (old.equals(update)) {
      logger.debug("Club déjà à jour");
    } else {
      try {
        manager.merge(update);
        this.listItems.set(this.listItems.indexOf(old), update);
      } catch (IllegalArgumentException e) {
        logger.error("Impossible de mettre à jour le club", e);
      }
    }
  }

  @Override
  public void delete(Club t) {
    try {
      manager.remove(t);
      this.listItems.remove(t);
    } catch (IllegalArgumentException e) {
      logger.error("Impossible de supprimer le club", e);
    }
  }

}
