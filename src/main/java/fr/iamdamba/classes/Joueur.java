package fr.iamdamba.classes;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public class Joueur {
  private String prenom;
  private String nom;
  private Year derniereSaison;
  private String code;
  private String PaysNaissance;
  private String villeNaissance;
  private String paysResidence;
  private LocalDate dateNaissance;
  private String positionSecondaire;
  private String position;
  private String pied;
  private String taille;
  private Double recordValeurMarche;
  private LocalDate dateExpirationContrat;
  private String nomAgent;
  private String url;

  private Club clubActuel;
  private List<ValeurMarche> valeurMarche;

  public Club getClubActuel() {
    return clubActuel;
  }

  public Double getRecordValeurMarche() {
    return valeurMarche.stream().mapToDouble(ValeurMarche::getValeur).max().orElse(0.0);
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Year getDerniereSaison() {
    return derniereSaison;
  }

  public void setDerniereSaison(Year derniereSaison) {
    this.derniereSaison = derniereSaison;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getPaysNaissance() {
    return PaysNaissance;
  }

  public void setPaysNaissance(String paysNaissance) {
    PaysNaissance = paysNaissance;
  }

  public String getVilleNaissance() {
    return villeNaissance;
  }

  public void setVilleNaissance(String villeNaissance) {
    this.villeNaissance = villeNaissance;
  }

  public String getPaysResidence() {
    return paysResidence;
  }

  public void setPaysResidence(String paysResidence) {
    this.paysResidence = paysResidence;
  }

  public LocalDate getDateNaissance() {
    return dateNaissance;
  }

  public void setDateNaissance(LocalDate dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

  public String getPositionSecondaire() {
    return positionSecondaire;
  }

  public void setPositionSecondaire(String positionSecondaire) {
    this.positionSecondaire = positionSecondaire;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getPied() {
    return pied;
  }

  public void setPied(String pied) {
    this.pied = pied;
  }

  public String getTaille() {
    return taille;
  }

  public void setTaille(String taille) {
    this.taille = taille;
  }

  public void setRecordValeurMarche(Double recordValeurMarche) {
    this.recordValeurMarche = recordValeurMarche;
  }

  public LocalDate getDateExpirationContrat() {
    return dateExpirationContrat;
  }

  public void setDateExpirationContrat(LocalDate dateExpirationContrat) {
    this.dateExpirationContrat = dateExpirationContrat;
  }

  public String getNomAgent() {
    return nomAgent;
  }

  public void setNomAgent(String nomAgent) {
    this.nomAgent = nomAgent;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setClubActuel(Club clubActuel) {
    this.clubActuel = clubActuel;
  }

  public List<ValeurMarche> getValeurMarche() {
    return valeurMarche;
  }

  public void setValeurMarche(List<ValeurMarche> valeurMarche) {
    this.valeurMarche = valeurMarche;
  }

}
