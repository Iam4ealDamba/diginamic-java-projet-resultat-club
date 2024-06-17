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
  @Id
  private Integer id;

  @Column(name = "code_club")
  private String codeClub;

  @Column(name = "nom")
  private String nom;

  @Column(name = "nb_joueur_equipe_national")
  private Integer nbJoueurEquipeNational;

  @Column(name = "nom_stade")
  private String nomStade;

  @Column(name = "nb_siege_stade")
  private Integer nbSiegeStade;

  @Column(name = "record_net_transfert")
  private String recordNetTransfert;

  @Column(name = "nom_coach")
  private String nomCoach;

  @Column(name = "derniere_saison")
  private Integer derniereSaison;

  @Column(name = "url")
  private String url;

  @ManyToOne
  @JoinColumn(name = "competition_id")
  private Competition competition;

  @OneToMany(mappedBy = "clubActuel")
  private List<Joueur> joueurs;

  @OneToMany(mappedBy = "clubJoueur")
  private List<Apparition> apparitions;

  @OneToMany(mappedBy = "club")
  private List<Composition> compositions;

  public Integer getTailleEffectif() {
    return joueurs.size();
  }

  public Double getMoyAgeEquipe() {
    List<Integer> ageJoueurs = new ArrayList<>();
    for (Joueur joueur : joueurs) {
      LocalDate today = LocalDate.now();
      Period period = Period.between(joueur.getDateNaissance(), today);
      ageJoueurs.add(period.getYears());
    }

    return ageJoueurs.stream().mapToInt(x -> x).average().getAsDouble();
  }

  public Long getNbEtranger() {
    return joueurs.stream().map(j -> !j.getPaysNaissance().equals(this.competition.getNomPays())).count();
  }

  public Double getPourcentageEtranger() {
    Long nbEtrangers = this.getNbEtranger();

    if (nbEtrangers != 0) {
      return (double) (nbEtrangers / this.getTailleEffectif()) * 100;
    }
    return 0.0;
  }

  public Integer getNbJoueurEquipeNational() {
    return nbJoueurEquipeNational;
  }

  @Override
  public String toString() {
    return "Club [id=" + id + ", codeClub=" + codeClub + ", nom=" + nom + ", nbJoueurEquipeNational="
        + nbJoueurEquipeNational + ", nomStade=" + nomStade + ", nbSiegeStade=" + nbSiegeStade + ", recordNetTransfert="
        + recordNetTransfert + ", nomCoach=" + nomCoach + ", derniereSaison=" + derniereSaison + ", url=" + url + "]";
  }

}
