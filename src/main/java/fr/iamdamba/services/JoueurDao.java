package fr.iamdamba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.Joueur;
import fr.iamdamba.services.interfaces.Dao;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;

public class JoueurDao implements Dao<Joueur> {
  /** Liste des apparitions */
  public List<Joueur> listItems;

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
  public JoueurDao(EntityManager manager, Logger logger) {
    this.manager = manager;
    this.logger = logger;

    this.listItems = this.all() == null ? new ArrayList<>() : this.all();
  }

  @Override
  public List<Joueur> all() {
    List<Joueur> query = manager.createQuery("SELECT t FROM Joueur t", Joueur.class).getResultList();
    if (query.stream().count() == 0) {
      return null;
    }
    return query;
  }

  @Override
  public Optional<Joueur> one(Object id) {
    Joueur query = manager.find(Joueur.class, id);

    return Optional.ofNullable(query);
  }

  @Override
  public void create(Joueur t) {
    try {
      manager.persist(t);
      this.listItems.add(t);
    } catch (EntityExistsException e) {
      logger.error("Impossible de creer le Joueur", e);
    }
  }

  @Override
  public void update(Joueur old, Joueur update) {
    if (old.equals(update)) {
      logger.debug("Joueur déjà à jour");
    } else {
      try {
        manager.merge(update);
        this.listItems.set(this.listItems.indexOf(old), update);
      } catch (IllegalArgumentException e) {
        logger.error("Impossible de mettre à jour le Joueur", e);
      }
    }
  }

  @Override
  public void delete(Joueur t) {
    try {
      manager.remove(t);
      this.listItems.remove(t);
    } catch (IllegalArgumentException e) {
      logger.error("Impossible de supprimer le Joueur", e);
    }
  }
}
