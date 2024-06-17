package fr.iamdamba.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class Joueur {
  @Id
  private Integer id;

  @Column(name = "prenom")
  private String prenom;

  @Column(name = "nom")
  private String nom;

  @Column(name = "derniere_saison")
  private Integer derniereSaison;

  @Column(name = "code")
  private String code;

  @Column(name = "Pays_naissance")
  private String PaysNaissance;

  @Column(name = "ville_naissance")
  private String villeNaissance;

  @Column(name = "pays_residence")
  private String paysResidence;

  @Column(name = "date_naissance")
  @Temporal(value = jakarta.persistence.TemporalType.DATE)
  private LocalDate dateNaissance;

  @Column(name = "position_secondaire")
  private String positionSecondaire;

  @Column(name = "position")
  private String position;

  @Column(name = "pied")
  private String pied;

  @Column(name = "taille")
  private Integer taille;

  @Column(name = "record_valeur_marche")
  private Double recordValeurMarche;

  @Column(name = "date_expiration_contrat")
  @Temporal(value = jakarta.persistence.TemporalType.TIMESTAMP)
  private LocalDateTime dateExpirationContrat;

  @Column(name = "nom_agent")
  private String nomAgent;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "url")
  private String url;

  @ManyToOne
  @JoinColumn(name = "club_id")
  private Club clubActuel;

  @OneToMany(mappedBy = "joueur")
  private List<ValeurMarche> valeurMarche;

  public Double getRecordValeurMarche() {
    return valeurMarche.stream().mapToDouble(ValeurMarche::getValeur).max().orElse(0.0);
  }

  public Competition getCompetitionClubActuel() {
    return this.clubActuel.getCompetition();
  }

  @Override
  public String toString() {
    return "Joueur [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", derniereSaison=" + derniereSaison + ", code="
        + code + ", PaysNaissance=" + PaysNaissance + ", villeNaissance=" + villeNaissance + ", paysResidence="
        + paysResidence + ", dateNaissance=" + dateNaissance + ", positionSecondaire=" + positionSecondaire
        + ", position=" + position + ", pied=" + pied + ", taille=" + taille + ", recordValeurMarche="
        + recordValeurMarche + ", dateExpirationContrat=" + dateExpirationContrat + ", nomAgent=" + nomAgent
        + ", imageUrl=" + imageUrl + ", url=" + url + "]";
  }

}
