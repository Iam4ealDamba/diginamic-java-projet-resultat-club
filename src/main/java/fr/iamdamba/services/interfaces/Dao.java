package fr.iamdamba.services.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityManager;

public interface Dao<T> {

  /** Retourne la liste de tous les objets de type T */
  List<T> all();

  /**
   * Retourne l'objet de type T dont l'id est passé en paramètre
   * 
   * @param id - l'id de l'objet (Integer, UUID ou String)
   */
  Optional<T> one(Object id);

  /**
   * Crée l'objet de type T et l'enregistre
   * 
   * @param t - l'objet à enregistrer
   */
  void create(T t);

  /**
   * Met à jour l'objet de type T
   * 
   * @param old    - l'ancien objet
   * @param update - le nouvel objet
   */
  void update(T old, T update);

  /**
   * Supprime l'objet de type T
   * 
   * @param t - l'objet à supprimer
   */
  void delete(T t);
}
