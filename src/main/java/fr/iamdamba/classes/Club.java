package fr.iamdamba.classes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Club {
  private String codeClub;
  private String nom;
  private Integer nbJoueurEquipeNational;
  private String nomStade;
  private Integer nbSiegeStade;
  private Double recordNetTransfert;
  private String nomCoach;
  private LocalDate derniereSaison;
  private String url;

  private Competition competition;
  private List<Joueur> joueurs;

  public String getCodeClub() {
    return codeClub;
  }

  public void setCodeClub(String codeClub) {
    this.codeClub = codeClub;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

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

  public Integer getNbEtranger() {
    return 0;
  }

  public Double getPourcentageEtranger() {
    return 0.0;
  }

  public Integer getNbJoueurEquipeNational() {
    return nbJoueurEquipeNational;
  }

  public String getNomStade() {
    return nomStade;
  }

  public void setNomStade(String nomStade) {
    this.nomStade = nomStade;
  }

  public Integer getNbSiegeStade() {
    return nbSiegeStade;
  }

  public void setNbSiegeStade(Integer nbSiegeStade) {
    this.nbSiegeStade = nbSiegeStade;
  }

  public Double getRecordNetTransfert() {
    return recordNetTransfert;
  }

  public void setRecordNetTransfert(Double recordNetTransfert) {
    this.recordNetTransfert = recordNetTransfert;
  }

  public String getNomCoach() {
    return nomCoach;
  }

  public void setNomCoach(String nomCoach) {
    this.nomCoach = nomCoach;
  }

  public LocalDate getDerniereSaison() {
    return derniereSaison;
  }

  public void setDerniereSaison(LocalDate derniereSaison) {
    this.derniereSaison = derniereSaison;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<Joueur> getJoueurs() {
    return joueurs;
  }

  public void setJoueurs(List<Joueur> joueurs) {
    this.joueurs = joueurs;
  }

  public Competition getCompetition() {
    return competition;
  }

  public void setCompetition(Competition competition) {
    this.competition = competition;
  }

}
