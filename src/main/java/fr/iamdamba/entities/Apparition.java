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
  @Id
  private String id;

  @Column(name = "nb_carton_rouge")
  private Integer nbCartonRouge;

  @Column(name = "nb_carton_jaune")
  private Integer nbCartonJaune;

  @Column(name = "nb_but")
  private Integer nbBut;

  @Column(name = "nb_assistance")
  private Integer nbAssistance;

  @Column(name = "temps_jouer")
  private Integer tempsJouer;

  @Column(name = "date")
  @Temporal(value = jakarta.persistence.TemporalType.DATE)
  private LocalDate date;

  @ManyToOne
  @JoinColumn(name = "match_id", nullable = true)
  private Match match;

  @ManyToOne
  @JoinColumn(name = "joueur_id", nullable = true)
  private Joueur joueur;

  @ManyToOne
  @JoinColumn(name = "club_id", nullable = true)
  private Club clubJoueur;

  @ManyToOne
  @JoinColumn(name = "competition_id", nullable = true)
  private Competition competition;

  public Club getClubJoueurActuel() {
    return this.joueur.getClubActuel();
  }

  @Override
  public String toString() {
    return "Apparition [id=" + id + ", nbCartonRouge=" + nbCartonRouge + ", nbCartonJaune=" + nbCartonJaune + ", nbBut="
        + nbBut + ", nbAssistance=" + nbAssistance + ", tempsJouer=" + tempsJouer + ", date=" + date + "]";
  }

}
