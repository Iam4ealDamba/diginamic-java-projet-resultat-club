package fr.iamdamba.classes;

import java.time.LocalDate;

public class Apparition {
  private Integer nbCartonRouge;
  private Integer nbCartonJaune;
  private Integer nbBut;
  private Integer nbAssistance;
  private Integer tempsJouer;
  private LocalDate date;

  private Match match;
  private Joueur joueur;
  private Club joueurClub;
  private Competition competition;

  public Joueur getJoueur() {
    return joueur;
  }

  public void setJoueur(Joueur joueur) {
    this.joueur = joueur;
  }

  public Match getMatch() {
    return match;
  }

  public void setMatch(Match match) {
    this.match = match;
  }

  public Integer getNbCartonRouge() {
    return nbCartonRouge;
  }

  public void setNbCartonRouge(Integer nbCartonRouge) {
    this.nbCartonRouge = nbCartonRouge;
  }

  public Integer getNbCartonJaune() {
    return nbCartonJaune;
  }

  public void setNbCartonJaune(Integer nbCartonJaune) {
    this.nbCartonJaune = nbCartonJaune;
  }

  public Integer getNbBut() {
    return nbBut;
  }

  public void setNbBut(Integer nbBut) {
    this.nbBut = nbBut;
  }

  public Integer getNbAssistance() {
    return nbAssistance;
  }

  public void setNbAssistance(Integer nbAssistance) {
    this.nbAssistance = nbAssistance;
  }

  public Integer getTempsJouer() {
    return tempsJouer;
  }

  public void setTempsJouer(Integer tempsJouer) {
    this.tempsJouer = tempsJouer;
  }

  public Club getJoueurClub() {
    return joueurClub;
  }

  public void setJoueurClub(Club joueurClub) {
    this.joueurClub = joueurClub;
  }

  public Club getJoueurClubActuel() {
    return joueur.getClubActuel();
  }

  public Competition getCompetition() {
    return competition;
  }

  public void setCompetition(Competition competition) {
    this.competition = competition;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

}