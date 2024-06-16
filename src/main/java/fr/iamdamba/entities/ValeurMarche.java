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
@ToString
@NoArgsConstructor
public class ValeurMarche {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "derniere_saison")
  private Integer derniereSaison;

  @Column(name = "date_heure")
  @Temporal(value = jakarta.persistence.TemporalType.TIMESTAMP)
  private LocalDateTime dateHeure;

  @Column(name = "date")
  @Temporal(value = jakarta.persistence.TemporalType.DATE)
  private LocalDate date;

  @Column(name = "date_semaine")
  @Temporal(value = jakarta.persistence.TemporalType.DATE)
  private LocalDate dateSemaine;

  @Column(name = "valeur")
  private Long valeur;

  @ManyToOne
  @JoinColumn(name = "joueur_id")
  private Joueur joueur;

  public Competition getCompetitionClubJoueur() {
    return joueur.getClubActuel().getCompetition();
  }

}
