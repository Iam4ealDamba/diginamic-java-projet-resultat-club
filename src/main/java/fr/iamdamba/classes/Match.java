package fr.iamdamba.classes;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public class Match {
  private Year saison;
  private Integer tour;
  private LocalDate date;
  private Integer nbButsDomicile;
  private Integer nbButsExterieur;
  private Integer domicilePosition;
  private Integer exterieurPosition;
  private String entraineurDomicile;
  private String entraineurExterieur;
  private String stade;
  private String arbitre;
  private String url;
  private String resultat;

  private Competition competition;
  private Club domicile;
  private Club exterieur;
  private List<Composition> compositions;

  public String getNomEquipeDomicile() {
    return this.domicile.getNom();
  }

  public String getNomEquipeExterieur() {
    return this.exterieur.getNom();
  }

  public String getTypeCompetition() {
    return this.competition.getType();
  }

  public List<Composition> getCompositionsDomicile() {
    return this.compositions;
  }

  public List<Composition> getCompositionsExterieur() {
    return this.compositions;
  }
}
