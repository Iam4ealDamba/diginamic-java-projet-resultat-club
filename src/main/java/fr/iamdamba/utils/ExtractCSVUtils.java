package fr.iamdamba.utils;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.Club;
import fr.iamdamba.entities.Competition;
import fr.iamdamba.entities.Composition;
import fr.iamdamba.entities.Evenement;
import fr.iamdamba.entities.Joueur;
import fr.iamdamba.entities.Match;
import fr.iamdamba.entities.ValeurMarche;

public class ExtractCSVUtils {
  /** Regex pour separer les chaines */
  private static String regexString = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

  /** Extrait les apparitions */
  public static List<Apparition> extractApparitions(FileReader reader) {
    Scanner scanner = new Scanner(reader);
    List<Apparition> apparitions = new ArrayList<>();

    // Passer la première ligne
    scanner.nextLine();

    // Lire le reste des lignes
    Integer count = 0;
    while (count < 100) {
      String[] lines = scanner.nextLine().split(regexString, -1);
      Apparition apparition = new Apparition();
      apparition.setId(lines[0]);
      apparition.setDate(lines[5].isEmpty() ? null : LocalDate.parse(lines[5]));
      apparition.setNbCartonJaune(lines[8].isEmpty() ? 0 : Integer.parseInt(lines[8]));
      apparition.setNbCartonRouge(lines[9].isEmpty() ? 0 : Integer.parseInt(lines[9]));
      apparition.setNbBut(lines[10].isEmpty() ? 0 : Integer.parseInt(lines[10]));
      apparition.setNbAssistance(lines[11].isEmpty() ? 0 : Integer.parseInt(lines[11]));
      apparition.setTempsJouer(lines[12].isEmpty() ? 0 : Integer.parseInt(lines[12]));

      // Ajout match
      Match match = new Match();
      match.setId(lines[1].isEmpty() ? 0 : Integer.parseInt(lines[1]));
      apparition.setMatch(match);

      // Ajout Joueur
      Joueur joueur = new Joueur();
      joueur.setId(lines[2].isEmpty() ? 0 : Integer.parseInt(lines[2]));
      apparition.setJoueur(joueur);

      // Ajout club
      Club club = new Club();
      club.setId(lines[3].isEmpty() ? 0 : Integer.parseInt(lines[3]));
      apparition.setClubJoueur(club);

      // Ajout competition
      Competition competition = new Competition();
      competition.setId(lines[7].isEmpty() ? "" : lines[7]);
      apparition.setCompetition(competition);

      apparitions.add(apparition);

      count++;
    }
    scanner.close();
    return apparitions;
  }

  /** Extrait les clubs */
  public static List<Club> extractClub(FileReader reader) {
    Scanner scanner = new Scanner(reader);
    List<Club> Clubs = new ArrayList<>();

    // Passer la première ligne
    scanner.nextLine();

    // Lire le reste des lignes
    while (scanner.hasNextLine()) {
      String[] lines = scanner.nextLine().split(regexString, -1);
      Club club = new Club();
      club.setId(Integer.parseInt(lines[0]));
      club.setCodeClub(lines[1]);
      club.setNom(lines[2]);
      club.setNbJoueurEquipeNational(Integer.parseInt(lines[9]));
      club.setNomStade(lines[9]);
      club.setNbSiegeStade(Integer.parseInt(lines[11]));
      club.setRecordNetTransfert(lines[12]);
      club.setNomCoach(lines[13]);
      club.setDerniereSaison(Integer.parseInt(lines[14]));
      club.setUrl(lines[15]);

      // Ajout competition
      Competition competition = new Competition();
      competition.setId(lines[3]);
      club.setCompetition(competition);

      Clubs.add(club);
    }
    scanner.close();

    return Clubs;
  }

  /** Extrait les competitions */
  public static List<Competition> extractCompetitions(FileReader reader) {
    Scanner scanner = new Scanner(reader);
    List<Competition> competitions = new ArrayList<>();

    // Passer la première ligne
    scanner.nextLine();

    // Lire le reste des lignes
    while (scanner.hasNextLine()) {
      String[] lines = scanner.nextLine().split(regexString, -1);
      Competition competition = new Competition();
      competition.setId((lines[0]));
      competition.setCompetitionCode(lines[1]);
      competition.setName(lines[2]);
      competition.setSousType(lines[3]);
      competition.setType(lines[4]);
      competition.setIdPays(Integer.parseInt(lines[5]));
      competition.setNomPays(lines[6]);
      competition.setCodeLigueHebergeur(lines[7]);
      competition.setConfederation(lines[8]);
      competition.setUrl(lines[9]);

      competitions.add(competition);
    }
    scanner.close();

    return competitions;
  }

  /** Extrait les compositions */
  public static List<Composition> extractCompositions(FileReader reader) {
    Scanner scanner = new Scanner(reader);
    List<Composition> Compositions = new ArrayList<>();

    // Passer la première ligne
    scanner.nextLine();

    // Lire le reste des lignes
    while (scanner.hasNextLine()) {
      String[] lines = scanner.nextLine().split(regexString, -1);
      Composition composition = new Composition();
      composition.setId(lines[0]);
      composition.setType(lines[3].isEmpty() ? "" : lines[3]);
      composition.setNumber(lines[4].isEmpty() ? "" : lines[4]);
      composition.setCapitaineEquipe(lines[7].isEmpty() ? false : Integer.parseInt(lines[7]) > 0 ? true : false);
      composition.setPosition(lines[8].isEmpty() ? "" : lines[8]);

      // Ajout match
      Match match = new Match();
      match.setId(lines[1].isEmpty() ? 0 : Integer.parseInt(lines[1]));
      composition.setMatch(match);

      // Ajout club
      Club club = new Club();
      club.setId(lines[2].isEmpty() ? 0 : Integer.parseInt(lines[2]));
      composition.setClub(club);

      // Ajout joueur
      Joueur joueur = new Joueur();
      joueur.setId(lines[5].isEmpty() ? 0 : Integer.parseInt(lines[5]));
      composition.setJoueur(joueur);

      Compositions.add(composition);
    }

    scanner.close();

    return Compositions;
  }

  /** Extrait les evenements */
  public static List<Evenement> extractEvenements(FileReader reader) {
    Scanner scanner = new Scanner(reader);
    List<Evenement> Evenements = new ArrayList<>();

    // Passer la première ligne
    scanner.nextLine();

    // Lire le reste des lignes
    Integer count = 0;
    while (count < 100) {
      String[] lines = scanner.nextLine().split(regexString, -1);
      Evenement evenement = new Evenement();
      evenement.setId(lines[0]);
      evenement.setDate(lines[1].isEmpty() ? null : LocalDate.parse(lines[1]));
      evenement.setMinutes(lines[3].isEmpty() ? 0 : Integer.parseInt(lines[3]));
      evenement.setType(lines[4].isEmpty() ? "" : lines[4]);
      evenement.setDescription(lines[7].isEmpty() ? "" : lines[7]);

      // Ajout match
      Match match = new Match();
      match.setId(lines[2].isEmpty() ? 0 : Integer.parseInt(lines[2]));
      evenement.setMatch(match);

      // Ajout club
      Club club = new Club();
      club.setId(lines[5].isEmpty() ? 0 : Integer.parseInt(lines[5]));
      evenement.setClub(club);

      // Ajout joueur
      Joueur joueur = new Joueur();
      joueur.setId(lines[6].isEmpty() ? 0 : Integer.parseInt(lines[6]));
      evenement.setJoueur(joueur);

      // Ajout remplacant
      Joueur remplacant = new Joueur();
      remplacant.setId(lines[8].isEmpty() ? 0 : Integer.parseInt(lines[8]));
      evenement.setJoueurRemplacant(remplacant);

      // Ajout assistant
      Joueur assistant = new Joueur();
      assistant.setId(lines[9].isEmpty() ? 0 : Integer.parseInt(lines[9]));
      evenement.setJoueurAssistant(assistant);

      Evenements.add(evenement);
      count++;
    }
    scanner.close();
    return Evenements;
  }

  /** Extrait les joueurs */
  public static List<Joueur> extractJoueurs(FileReader reader) {
    Scanner scanner = new Scanner(reader);
    List<Joueur> Joueurs = new ArrayList<>();

    // Passer la première ligne
    scanner.nextLine();

    // Lire le reste des lignes
    while (scanner.hasNextLine()) {
      String[] lines = scanner.nextLine().split(regexString, -1);
      Joueur joueur = new Joueur();
      joueur.setId(Integer.parseInt(lines[0]));
      joueur.setPrenom(lines[1].isEmpty() ? "" : lines[1]);
      joueur.setNom(lines[2].isEmpty() ? "" : lines[2]);
      joueur.setDerniereSaison(Integer.parseInt(lines[4]));
      joueur.setCode(lines[6].isEmpty() ? "" : lines[6]);
      joueur.setPaysNaissance(lines[7].isEmpty() ? "" : lines[7]);
      joueur.setVilleNaissance(lines[8].isEmpty() ? "" : lines[8]);
      joueur.setPaysResidence(lines[9].isEmpty() ? "" : lines[9]);
      joueur.setDateNaissance(lines[10].isEmpty() ? null : LocalDate.parse(lines[10]));
      joueur.setPositionSecondaire(lines[11].isEmpty() ? "" : lines[11]);
      joueur.setPosition(lines[12].isEmpty() ? "" : lines[12]);
      joueur.setPied(lines[13].isEmpty() ? "" : lines[13]);
      joueur.setTaille(lines[14].length() == 0 ? 0 : Integer.parseInt(lines[14]));
      joueur.setRecordValeurMarche(lines[16].isEmpty() ? 0 : Double.parseDouble(lines[16]));
      joueur
          .setDateExpirationContrat(lines[17].length() == 0 ? null : LocalDateTime.parse(lines[17].replace(" ", "T")));
      joueur.setNomAgent(lines[18].isEmpty() ? "" : lines[18]);
      joueur.setImageUrl(lines[19].isEmpty() ? "" : lines[19]);
      joueur.setUrl(lines[20].isEmpty() ? "" : lines[20]);

      // Ajout club
      Club club = new Club();
      club.setId(Integer.parseInt(lines[5]));
      joueur.setClubActuel(club);

      Joueurs.add(joueur);
    }
    scanner.close();
    return Joueurs;
  }

  /** Extrait les matchs */
  public static List<Match> extractMatchs(FileReader reader) {
    Scanner scanner = new Scanner(reader);
    List<Match> Matchs = new ArrayList<>();

    // Passer la première ligne
    scanner.nextLine();

    // Lire le reste des lignes
    while (scanner.hasNextLine()) {
      String[] lines = scanner.nextLine().split(regexString, -1);
      Match match = new Match();

      match.setId(Integer.parseInt(lines[0]));
      match.setSaison(lines[2].isEmpty() ? 0 : Integer.parseInt(lines[2]));
      match.setTour(lines[3].isEmpty() ? "" : lines[3]);
      match.setDate(lines[4].isEmpty() ? null : LocalDate.parse(lines[4]));
      match.setNbButsDomicile(lines[7].isEmpty() ? 0 : Integer.parseInt(lines[7]));
      match.setNbButsExterieur(lines[8].isEmpty() ? 0 : Integer.parseInt(lines[8]));
      match.setDomicilePosition(lines[9].isEmpty() ? 0 : Integer.parseInt(lines[9]));
      match.setExterieurPosition(lines[10].isEmpty() ? 0 : Integer.parseInt(lines[10]));
      match.setEntraineurDomicile(lines[11].isEmpty() ? "" : lines[11]);
      match.setEntraineurExterieur(lines[12].isEmpty() ? "" : lines[12]);
      match.setStade(lines[13].isEmpty() ? "" : lines[13]);
      match.setArbitre(lines[15].isEmpty() ? "" : lines[15]);
      match.setUrl(lines[16].isEmpty() ? "" : lines[16]);
      match.setScore(lines[21].isEmpty() ? "" : lines[21]);

      // Ajout competition
      Competition competition = new Competition();
      competition.setId(lines[1]);
      match.setCompetition(competition);

      // Ajout domicile
      Club domicile = new Club();
      domicile.setId(Integer.parseInt(lines[5]));
      match.setDomicile(domicile);

      // Ajout exterieur
      Club exterieur = new Club();
      exterieur.setId(Integer.parseInt(lines[6]));
      match.setExterieur(exterieur);

      Matchs.add(match);
    }
    scanner.close();
    return Matchs;
  }

  /** Extrait les valeur marche */
  public static List<ValeurMarche> extractValeurMarches(FileReader reader) {
    Scanner scanner = new Scanner(reader);
    List<ValeurMarche> ValeurMarches = new ArrayList<>();

    // Passer la première ligne
    scanner.nextLine();

    // Lire le reste des lignes
    while (scanner.hasNextLine()) {
      String[] lines = scanner.nextLine().split(regexString, -1);
      ValeurMarche valeurMarche = new ValeurMarche();
      valeurMarche.setDerniereSaison(Integer.parseInt(lines[1]));
      valeurMarche.setDateHeure(LocalDateTime.parse(lines[2].replace(" ", "T")));
      valeurMarche.setDate(LocalDate.parse(lines[3]));
      valeurMarche.setDateSemaine(LocalDate.parse(lines[4]));
      valeurMarche.setValeur(Long.parseLong(lines[5]));

      // Ajout joueur
      Joueur joueur = new Joueur();
      joueur.setId(Integer.parseInt(lines[0]));
      valeurMarche.setJoueur(joueur);

      ValeurMarches.add(valeurMarche);

    }
    scanner.close();
    return ValeurMarches;
  }

}
