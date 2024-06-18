package fr.iamdamba.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Evenement {
  /** L'identifiant. */
  @Id
  private String id;
  /** La date. */
  @Column(name = "date")
  private LocalDate date;
  /** Les minutes. */
  @Column(name = "minutes")
  private Integer minutes;
  /** Le type. */
  @Column(name = "type")
  private String type;
  /** La description. */
  @Column(name = "description")
  private String description;
  /** Le match. */
  @ManyToOne
  @JoinColumn(name = "match_id")
  private Match match;
  /** Le club. */
  @ManyToOne
  @JoinColumn(name = "club_id")
  private Club club;
  /** Le joueur. */
  @ManyToOne
  @JoinColumn(name = "joueur_id")
  private Joueur joueur;
  /** Le joueur assistant. */
  @ManyToOne
  @JoinColumn(name = "joueur_assistant_id", nullable = true)
  private Joueur joueurAssistant;
  /** Le joueur remplacant. */
  @ManyToOne
  @JoinColumn(name = "joueur_remplacant_id", nullable = true)
  private Joueur joueurRemplacant;

  /** Affichage de l'evenement */
  @Override
  public String toString() {
    return "Evenement [id=" + id + ", date=" + date + ", minutes=" + minutes + ", type=" + type + ", description="
        + description + "]";
  }

}
