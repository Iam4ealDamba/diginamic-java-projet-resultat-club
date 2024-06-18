package fr.iamdamba.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Temporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ValeurMarche {
  /** L'identifiant. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  /** La derniere saison */
  @Column(name = "derniere_saison")
  private Integer derniereSaison;
  /** La date et l'heure */
  @Column(name = "date_heure")
  @Temporal(value = jakarta.persistence.TemporalType.TIMESTAMP)
  private LocalDateTime dateHeure;
  /** La date */
  @Column(name = "date")
  @Temporal(value = jakarta.persistence.TemporalType.DATE)
  private LocalDate date;
  /** La date de la semaine */
  @Column(name = "date_semaine")
  @Temporal(value = jakarta.persistence.TemporalType.DATE)
  private LocalDate dateSemaine;
  /** La valeur marche */
  @Column(name = "valeur")
  private Long valeur;
  /** Le joueur associe a cette valeur */
  @ManyToOne
  @JoinColumn(name = "joueur_id")
  private Joueur joueur;

  /** La competition associee au club du joueur */
  public Competition getCompetitionClubJoueur() {
    return joueur.getClubActuel().getCompetition();
  }

  /** Affichage de la valeur marche */
  @Override
  public String toString() {
    return "ValeurMarche [id=" + id + ", derniereSaison=" + derniereSaison + ", dateHeure=" + dateHeure + ", date="
        + date + ", dateSemaine=" + dateSemaine + ", valeur=" + valeur + "]";
  }

}
