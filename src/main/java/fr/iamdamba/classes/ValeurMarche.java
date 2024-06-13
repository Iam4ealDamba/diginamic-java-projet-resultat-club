package fr.iamdamba.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

public class ValeurMarche {
  private Year derniereSaison;
  private LocalDateTime dateHeure;
  private LocalDate date;
  private LocalDate dateSemaine;
  private Long valeur;

  private Joueur joueur;

  public Competition getJoueurClubCompetition() {
    return joueur.getClubActuel().getCompetition();
  }

  public Year getDerniereSaison() {
    return derniereSaison;
  }

  public void setDerniereSaison(Year derniereSaison) {
    this.derniereSaison = derniereSaison;
  }

  public LocalDateTime getDateHeure() {
    return dateHeure;
  }

  public void setDateHeure(LocalDateTime dateHeure) {
    this.dateHeure = dateHeure;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalDate getDateSemaine() {
    return dateSemaine;
  }

  public void setDateSemaine(LocalDate dateSemaine) {
    this.dateSemaine = dateSemaine;
  }

  public Long getValeur() {
    return valeur;
  }

  public void setValeur(Long valeur) {
    this.valeur = valeur;
  }

  public Joueur getJoueur() {
    return joueur;
  }

  public void setJoueur(Joueur joueur) {
    this.joueur = joueur;
  }

}
