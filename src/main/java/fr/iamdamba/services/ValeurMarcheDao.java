package fr.iamdamba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.ValeurMarche;
import fr.iamdamba.services.interfaces.Dao;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;

public class ValeurMarcheDao implements Dao<ValeurMarche> {
  /** Liste des apparitions */
  public List<ValeurMarche> listItems;

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
  public ValeurMarcheDao(EntityManager manager, Logger logger) {
    this.manager = manager;
    this.logger = logger;
    this.listItems = this.all() == null ? new ArrayList<>() : this.all();
  }

  @Override
  public List<ValeurMarche> all() {
    List<ValeurMarche> query = manager.createQuery("SELECT t FROM ValeurMarche t", ValeurMarche.class).getResultList();
    if (query.stream().count() == 0) {
      return null;
    }
    return query;
  }

  @Override
  public Optional<ValeurMarche> one(Object id) {
    ValeurMarche query = manager.find(ValeurMarche.class, id);

    return Optional.ofNullable(query);
  }

  @Override
  public void create(ValeurMarche t) {
    try {
      manager.persist(t);
      this.listItems.add(t);
    } catch (EntityExistsException e) {
      logger.error("Impossible de creer le ValeurMarche", e);
    }
  }

  @Override
  public void update(ValeurMarche old, ValeurMarche update) {
    if (old.equals(update)) {
      logger.debug("ValeurMarche déjà à jour");
    } else {
      try {
        manager.merge(update);
        this.listItems.set(this.listItems.indexOf(old), update);
      } catch (IllegalArgumentException e) {
        logger.error("Impossible de mettre à jour le ValeurMarche", e);
      }
    }
  }

  @Override
  public void delete(ValeurMarche t) {
    try {
      manager.remove(t);
      this.listItems.remove(t);
    } catch (IllegalArgumentException e) {
      logger.error("Impossible de supprimer le ValeurMarche", e);
    }
  }

}
