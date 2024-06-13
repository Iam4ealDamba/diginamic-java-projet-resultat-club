package fr.iamdamba.classes;

import java.util.List;

public class Competition { 
  private String competition_code;
  private String name;
  private String sous_type;
  private String type;
  private Integer idPays;
  private String nomPays;
  private String codeLigueHebergeur;
  private String confederation;
  private String url; 

  private List<Club> clubs;
  private List<Match> matches;
  private List<Apparition> apparitions;
  
  public String getCompetition_code() {
    return competition_code;
  }
  public void setCompetition_code(String competition_code) {
    this.competition_code = competition_code;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getSous_type() {
    return sous_type;
  }
  public void setSous_type(String sous_type) {
    this.sous_type = sous_type;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public Integer getIdPays() {
    return idPays;
  }
  public void setIdPays(Integer idPays) {
    this.idPays = idPays;
  }
  public String getNomPays() {
    return nomPays;
  }
  public void setNomPays(String nomPays) {
    this.nomPays = nomPays;
  }
  public String getCodeLigueHebergeur() {
    return codeLigueHebergeur;
  }
  public void setCodeLigueHebergeur(String codeLigueHebergeur) {
    this.codeLigueHebergeur = codeLigueHebergeur;
  }
  public String getConfederation() {
    return confederation;
  }
  public void setConfederation(String confederation) {
    this.confederation = confederation;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public List<Club> getClubs() {
    return clubs;
  }
  public void setClubs(List<Club> clubs) {
    this.clubs = clubs;
  }
  public List<Match> getMatches() {
    return matches;
  }
  public void setMatches(List<Match> matches) {
    this.matches = matches;
  }
  public List<Apparition> getApparitions() {
    return apparitions;
  }
  public void setApparitions(List<Apparition> apparitions) {
    this.apparitions = apparitions;
  }

  
}
