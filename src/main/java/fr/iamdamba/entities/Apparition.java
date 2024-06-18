package fr.iamdamba.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Apparition {
  /** Identifiant de l'apparition */
  @Id
  private String id;

  /** Nombre de cartons rouge */
  @Column(name = "nb_carton_rouge")
  private Integer nbCartonRouge;

  /** Nombre de cartons jaunes */
  @Column(name = "nb_carton_jaune")
  private Integer nbCartonJaune;

  /** Nombre de but */
  @Column(name = "nb_but")
  private Integer nbBut;

  /** Nombre d'assistance */
  @Column(name = "nb_assistance")
  private Integer nbAssistance;

  /** Temps de jeu */
  @Column(name = "temps_jouer")
  private Integer tempsJouer;

  /** Date */
  @Column(name = "date")
  @Temporal(value = jakarta.persistence.TemporalType.DATE)
  private LocalDate date;

  /** Match associe a l'apparition */
  @ManyToOne
  @JoinColumn(name = "match_id", nullable = true)
  private Match match;

  /** Joueur associe a l'apparition */
  @ManyToOne
  @JoinColumn(name = "joueur_id", nullable = true)
  private Joueur joueur;

  /** Club associe a l'apparition */
  @ManyToOne
  @JoinColumn(name = "club_id", nullable = true)
  private Club clubJoueur;

  /** Competition associe a l'apparition */
  @ManyToOne
  @JoinColumn(name = "competition_id", nullable = true)
  private Competition competition;

  /** Joueur actuel */
  public Club getClubJoueurActuel() {
    return this.joueur.getClubActuel();
  }

  /** Affichage l'apparition */
  @Override
  public String toString() {
    return "Apparition [id=" + id + ", nbCartonRouge=" + nbCartonRouge + ", nbCartonJaune=" + nbCartonJaune + ", nbBut="
        + nbBut + ", nbAssistance=" + nbAssistance + ", tempsJouer=" + tempsJouer + ", date=" + date + "]";
  }

}
