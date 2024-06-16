package fr.iamdamba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.Match;
import fr.iamdamba.services.interfaces.Dao;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;

public class MatchDao implements Dao<Match> {
  /** Liste des apparitions */
  public List<Match> listItems;

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
  public MatchDao(EntityManager manager, Logger logger) {
    this.manager = manager;
    this.logger = logger;

    this.listItems = this.all() == null ? new ArrayList<>() : this.all();
  }

  @Override
  public List<Match> all() {
    List<Match> query = manager.createQuery("SELECT t FROM Match t", Match.class).getResultList();
    if (query.stream().count() == 0) {
      return null;
    }
    return query;
  }

  @Override
  public Optional<Match> one(Object id) {
    Match query = manager.find(Match.class, id);

    return Optional.ofNullable(query);
  }

  @Override
  public void create(Match t) {
    try {
      manager.persist(t);
      this.listItems.add(t);
    } catch (EntityExistsException e) {
      logger.error("Impossible de creer le Match", e);
    }
  }

  @Override
  public void update(Match old, Match update) {
    if (old.equals(update)) {
      logger.debug("Match déjà à jour");
    } else {
      try {
        manager.merge(update);
        this.listItems.set(this.listItems.indexOf(old), update);
      } catch (IllegalArgumentException e) {
        logger.error("Impossible de mettre à jour le Match", e);
      }
    }
  }

  @Override
  public void delete(Match t) {
    try {
      manager.remove(t);
      this.listItems.remove(t);
    } catch (IllegalArgumentException e) {
      logger.error("Impossible de supprimer le Match", e);
    }
  }

}
