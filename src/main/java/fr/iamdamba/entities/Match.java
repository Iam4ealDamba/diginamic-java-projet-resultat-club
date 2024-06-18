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
@NoArgsConstructor
public class Match {
  /** L'identifiant. */
  @Id
  private Integer id;
  /** L'annee */
  @Column(name = "saison")
  private Integer saison;
  /** Le tour */
  @Column(name = "tour")
  private String tour;
  /** La date */
  @Column(name = "date")
  @Temporal(value = jakarta.persistence.TemporalType.DATE)
  private LocalDate date;
  /** Le nombre de buts */
  @Column(name = "nb_buts_domicile")
  private Integer nbButsDomicile;
  /** Le nombre de buts */
  @Column(name = "nb_buts_exterieur")
  private Integer nbButsExterieur;
  /** La position */
  @Column(name = "domicile_position")
  private Integer domicilePosition;
  /** La position */
  @Column(name = "exterieur_position")
  private Integer exterieurPosition;
  /** L'entraineur */
  @Column(name = "entraineur_domicile")
  private String entraineurDomicile;
  /** L'entraineur */
  @Column(name = "entraineur_exterieur")
  private String entraineurExterieur;
  /** Le stade */
  @Column(name = "stade")
  private String stade;
  /** L'arbitre */
  @Column(name = "arbitre")
  private String arbitre;
  /** L'url */
  @Column(name = "url")
  private String url;
  /** Le score */
  @Column(name = "score")
  private String score;
  /** La competition */
  @ManyToOne
  @JoinColumn(name = "competition_id")
  private Competition competition;
  /** La competition */
  @ManyToOne
  @JoinColumn(name = "club_domicile_id")
  private Club domicile;
  /** La competition */
  @ManyToOne
  @JoinColumn(name = "club_exterieur_id")
  private Club exterieur;
  /** Les apparitions */
  @OneToMany(mappedBy = "match")
  private List<Apparition> apparitions;
  /** Les compositions */
  @OneToMany(mappedBy = "match")
  private List<Composition> compositions;

  /** Affichage du match */
  @Override
  public String toString() {
    return "Match [id=" + id + ", saison=" + saison + ", tour=" + tour + ", date=" + date + ", nbButsDomicile="
        + nbButsDomicile + ", nbButsExterieur=" + nbButsExterieur + ", domicilePosition=" + domicilePosition
        + ", exterieurPosition=" + exterieurPosition + ", entraineurDomicile=" + entraineurDomicile
        + ", entraineurExterieur=" + entraineurExterieur + ", stade=" + stade + ", arbitre=" + arbitre + ", url=" + url
        + ", score=" + score + "]";
  }

}
