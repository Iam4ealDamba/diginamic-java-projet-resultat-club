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
  /** L'id du joueur */
  @Id
  private Integer id;
  /** Le prenom */
  @Column(name = "prenom")
  private String prenom;
  /** Le nom */
  @Column(name = "nom")
  private String nom;
  /** La derniere saison */
  @Column(name = "derniere_saison")
  private Integer derniereSaison;
  /** Le code */
  @Column(name = "code")
  private String code;
  /** Le pays de naissance */
  @Column(name = "Pays_naissance")
  private String PaysNaissance;
  /** La ville de naissance */
  @Column(name = "ville_naissance")
  private String villeNaissance;
  /** Le pays de residence */
  @Column(name = "pays_residence")
  private String paysResidence;
  /** La date de naissance */
  @Column(name = "date_naissance")
  @Temporal(value = jakarta.persistence.TemporalType.DATE)
  private LocalDate dateNaissance;
  /** La position secondaire */
  @Column(name = "position_secondaire")
  private String positionSecondaire;
  /** La position */
  @Column(name = "position")
  private String position;
  /** Le pied */
  @Column(name = "pied")
  private String pied;
  /** La taille */
  @Column(name = "taille")
  private Integer taille;
  /** Le record valeur marche */
  @Column(name = "record_valeur_marche")
  private Double recordValeurMarche;
  /** La date d'expiration du contrat */
  @Column(name = "date_expiration_contrat")
  @Temporal(value = jakarta.persistence.TemporalType.TIMESTAMP)
  private LocalDateTime dateExpirationContrat;
  /** Le nom de l'agent */
  @Column(name = "nom_agent")
  private String nomAgent;
  /** L'url de l'image */
  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "url")
  private String url;
  /** Le club actuel */
  @ManyToOne
  @JoinColumn(name = "club_id")
  private Club clubActuel;
  /** Les valeurs du marche */
  @OneToMany(mappedBy = "joueur")
  private List<ValeurMarche> valeurMarche;

  /** Le record valeur marche */
  public Double getRecordValeurMarche() {
    return valeurMarche.stream().mapToDouble(ValeurMarche::getValeur).max().orElse(0.0);
  }

  /** Le club actuel */
  public Competition getCompetitionClubActuel() {
    return this.clubActuel.getCompetition();
  }

  /** L'affichage du joueur */
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
