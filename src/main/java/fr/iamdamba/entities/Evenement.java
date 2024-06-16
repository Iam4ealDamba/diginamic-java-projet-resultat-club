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
@ToString
@NoArgsConstructor
public class Evenement {
  @Id 
  private String id;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "minutes")
  private Integer minutes;

  @Column(name = "type")
  private String type;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "match_id")
  private Match match;

  @ManyToOne
  @JoinColumn(name = "club_id")
  private Club club;

  @ManyToOne
  @JoinColumn(name = "joueur_id")
  private Joueur joueur;

  @ManyToOne
  @JoinColumn(name = "joueur_assistant_id", nullable = true)
  private Joueur joueurAssistant;

  @ManyToOne
  @JoinColumn(name = "joueur_remplacant_id", nullable = true)
  private Joueur joueurRemplacant;

}
