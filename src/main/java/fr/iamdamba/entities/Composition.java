package fr.iamdamba.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Composition {
  /** L'identifiant. */
  @Id
  private String id;
  /** Le type. */
  @Column(name = "type")
  private String type;
  /** Le numéro. */
  @Column(name = "number")
  private String number;
  /** La position. */
  @Column(name = "position")
  private String position;
  /** Le capitaine de l'équipe. */
  @Column(name = "capitaine_equipe")
  private Boolean capitaineEquipe;
  /** L'équipe. */
  @ManyToOne
  @JoinColumn(name = "club_id")
  private Club club;
  /** Le match. */
  @ManyToOne
  @JoinColumn(name = "match_id")
  private Match match;
  /** Le joueur. */
  @ManyToOne
  @JoinColumn(name = "joueur_id")
  private Joueur joueur;

  /** Affichage de la composition. */
  @Override
  public String toString() {
    return "Composition [id=" + id + ", type=" + type + ", number=" + number + ", position=" + position
        + ", capitaineEquipe=" + capitaineEquipe + "]";
  }

}
