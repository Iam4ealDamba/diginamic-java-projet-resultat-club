package fr.iamdamba.classes;

public class Composition {
  private String type;
  private String position;
  private Boolean capitaineEquipe;

  private Match match;
  private Joueur joueur;

  public Club getClub() {
    return new Club();
  }
}
  