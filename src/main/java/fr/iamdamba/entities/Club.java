package fr.iamdamba.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Club {
  /** L'id du club */
  @Id
  private Integer id;

  /** Le code du club */
  @Column(name = "code_club")
  private String codeClub;

  @Column(name = "nom")
  private String nom;

  /** Le nombre de joueurs du club */
  @Column(name = "nb_joueur_equipe_national")
  private Integer nbJoueurEquipeNational;

  /** Le nom du stade */
  @Column(name = "nom_stade")
  private String nomStade;

  /** Le nombre de sieges */
  @Column(name = "nb_siege_stade")
  private Integer nbSiegeStade;

  /** Le record du club */
  @Column(name = "record_net_transfert")
  private String recordNetTransfert;

  /** Le nom du coach */
  @Column(name = "nom_coach")
  private String nomCoach;

  /** La derniere saison */
  @Column(name = "derniere_saison")
  private Integer derniereSaison;

  /** L'url */
  @Column(name = "url")
  private String url;

  /** La competition */
  @ManyToOne
  @JoinColumn(name = "competition_id")
  private Competition competition;

  /** La liste des joueurs */
  @OneToMany(mappedBy = "clubActuel")
  private List<Joueur> joueurs;
  /** La liste des apparitions */
  @OneToMany(mappedBy = "clubJoueur")
  private List<Apparition> apparitions;

  /** La liste des compositions */
  @OneToMany(mappedBy = "club")
  private List<Composition> compositions;

  /** La liste des matchs du club à domicile */
  @OneToMany(mappedBy = "domicile")
  private List<Match> matchDomicile;

  /** La liste des matchs du club à l'extérieur */
  @OneToMany(mappedBy = "exterieur")
  private List<Match> matchExterieur;

  /** La taille de l'effectif */
  public Integer getTailleEffectif() {
    return joueurs.size();
  }

  /** La moyenne de l'age des joueurs */
  public Double getMoyAgeEquipe() {
    List<Integer> ageJoueurs = new ArrayList<>();
    for (Joueur joueur : joueurs) {
      LocalDate today = LocalDate.now();
      Period period = Period.between(joueur.getDateNaissance(), today);
      ageJoueurs.add(period.getYears());
    }

    return ageJoueurs.stream().mapToInt(x -> x).average().getAsDouble();
  }

  /** Nombre de joueurs etrangers */
  public Long getNbEtranger() {
    return joueurs.stream().map(j -> !j.getPaysNaissance().equals(this.competition.getNomPays())).count();
  }

  /** Pourcentage de joueurs etrangers */
  public Double getPourcentageEtranger() {
    Long nbEtrangers = this.getNbEtranger();

    if (nbEtrangers != 0) {
      return (double) (nbEtrangers / this.getTailleEffectif()) * 100;
    }
    return 0.0;
  }

  /** Nombre de joueurs national */
  public Integer getNbJoueurEquipeNational() {
    return nbJoueurEquipeNational;
  }

  /** Affiche le club */
  @Override
  public String toString() {
    return "Club [id=" + id + ", nom=" + nom + ", nbJoueurEquipeNational=" + nbJoueurEquipeNational + ", nomStade="
        + nomStade + ", nbSiegeStade=" + nbSiegeStade + ", recordNetTransfert=" + recordNetTransfert + ", nomCoach="
        + nomCoach + ", derniereSaison=" + derniereSaison + ", url=" + url + "]";
  }

}
