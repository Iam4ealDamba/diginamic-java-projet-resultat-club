package fr.iamdamba.entities;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Match {
  @Id
  private Integer id;

  @Column(name = "saison")
  private Integer saison;

  @Column(name = "tour")
  private String tour;

  @Column(name = "date")
  @Temporal(value = jakarta.persistence.TemporalType.DATE)
  private LocalDate date;

  @Column(name = "nb_buts_domicile")
  private Integer nbButsDomicile;

  @Column(name = "nb_buts_exterieur")
  private Integer nbButsExterieur;

  @Column(name = "domicile_position")
  private Integer domicilePosition;

  @Column(name = "exterieur_position")
  private Integer exterieurPosition;

  @Column(name = "entraineur_domicile")
  private String entraineurDomicile;

  @Column(name = "entraineur_exterieur")
  private String entraineurExterieur;

  @Column(name = "stade")
  private String stade;

  @Column(name = "arbitre")
  private String arbitre;

  @Column(name = "url")
  private String url;

  @Column(name = "score")
  private String score;

  @ManyToOne
  @JoinColumn(name = "competition_id")
  private Competition competition;

  @ManyToOne
  @JoinColumn(name = "club_domicile_id")
  private Club domicile;

  @ManyToOne
  @JoinColumn(name = "club_exterieur_id")
  private Club exterieur;

  @OneToMany(mappedBy = "match")
  private List<Apparition> apparitions;

  @OneToMany(mappedBy = "match")
  private List<Composition> compositions;

}
