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
  @Id
  private String id;

  @Column(name = "type")
  private String type;

  @Column(name = "number")
  private String number;

  @Column(name = "position")
  private String position;

  @Column(name = "capitaine_equipe")
  private Boolean capitaineEquipe;

  @ManyToOne
  @JoinColumn(name = "club_id")
  private Club club;

  @ManyToOne
  @JoinColumn(name = "match_id")
  private Match match;

  @ManyToOne
  @JoinColumn(name = "joueur_id")
  private Joueur joueur;

  @Override
  public String toString() {
    return "Composition [id=" + id + ", type=" + type + ", number=" + number + ", position=" + position
        + ", capitaineEquipe=" + capitaineEquipe + "]";
  }

}
