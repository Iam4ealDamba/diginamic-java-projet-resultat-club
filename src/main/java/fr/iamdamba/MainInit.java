package fr.iamdamba;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iamdamba.configs.JpaConfig;
import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.Club;
import fr.iamdamba.entities.Competition;
import fr.iamdamba.entities.Composition;
import fr.iamdamba.entities.Evenement;
import fr.iamdamba.entities.Joueur;
import fr.iamdamba.entities.Match;
import fr.iamdamba.entities.ValeurMarche;
import fr.iamdamba.services.ApparitionDao;
import fr.iamdamba.services.ClubDao;
import fr.iamdamba.services.CompetitionDao;
import fr.iamdamba.services.CompositionDao;
import fr.iamdamba.services.EvenementDao;
import fr.iamdamba.services.JoueurDao;
import fr.iamdamba.services.MatchDao;
import fr.iamdamba.services.ValeurMarcheDao;
import fr.iamdamba.utils.ExtractCSVUtils;

public class MainInit {
	public static void main(String[] args) throws FileNotFoundException {
		// #region Initialisation Variables de l'application
		Logger logger = LoggerFactory.getLogger(MainInit.class);
		JpaConfig jpaConfig = new JpaConfig("diginamic-resultat-club-init", logger);
		// #endregion

		// #region Initialisation fichier CSV
		String pathCSVApparition = MainInit.class.getClassLoader().getResource("./csv/8.appearances.csv")
				.getPath();
		String pathCSVClub = MainInit.class.getClassLoader().getResource("./csv/2.clubs.csv").getPath();
		String pathCSVCompetition = MainInit.class.getClassLoader().getResource("csv/1.competitions.csv")
				.getPath();
		String pathCSVComposition = MainInit.class.getClassLoader().getResource("./csv/7.game_lineups.csv")
				.getPath();
		String pathCSVEvenement = MainInit.class.getClassLoader().getResource("./csv/6.game_events.csv")
				.getPath();
		String pathCSVJoueur = MainInit.class.getClassLoader().getResource("./csv/3.players.csv").getPath();
		String pathCSVMatch = MainInit.class.getClassLoader().getResource("./csv/5.games.csv").getPath();
		String pathCSVValeurMarche = MainInit.class.getClassLoader()
				.getResource("./csv/4.player_valuations.csv")
				.getPath();

		FileReader readerCSVApparition = new FileReader(pathCSVApparition);
		FileReader readerCSVClub = new FileReader(pathCSVClub);
		FileReader readerCSVCompetition = new FileReader(pathCSVCompetition);
		FileReader readerCSVComposition = new FileReader(pathCSVComposition);
		FileReader readerCSVEvenement = new FileReader(pathCSVEvenement);
		FileReader readerCSVJoueur = new FileReader(pathCSVJoueur);
		FileReader readerCSVMatch = new FileReader(pathCSVMatch);
		FileReader readerCSVValeurMarche = new FileReader(pathCSVValeurMarche);

		List<Apparition> apparitionsFromCSV = ExtractCSVUtils.extractApparitions(readerCSVApparition);
		List<Club> clubsFromCSV = ExtractCSVUtils.extractClub(readerCSVClub);
		List<Competition> competitionsFromCSV = ExtractCSVUtils.extractCompetitions(readerCSVCompetition);
		List<Composition> compositionsFromCSV = ExtractCSVUtils.extractCompositions(readerCSVComposition);
		List<Evenement> evenementsFromCSV = ExtractCSVUtils.extractEvenements(readerCSVEvenement);
		List<Joueur> joueursFromCSV = ExtractCSVUtils.extractJoueurs(readerCSVJoueur);
		List<Match> matchesFromCSV = ExtractCSVUtils.extractMatchs(readerCSVMatch);
		List<ValeurMarche> valeursMarcheFromCSV = ExtractCSVUtils.extractValeurMarches(readerCSVValeurMarche);
		// #endregion

		// #region Transaction des competitions
		jpaConfig.startTransaction();
		CompetitionDao competitionDao = new CompetitionDao(jpaConfig.getManager(), logger);

		// Insertion des competitions
		for (Competition competition : competitionsFromCSV) {
			competitionDao.create(competition);
		}

		jpaConfig.commitTransaction();
		// #endregion

		// #region Transaction des clubs
		jpaConfig.startTransaction();

		ClubDao clubDao = new ClubDao(jpaConfig.getManager(), logger);

		// Insertion des clubs
		for (Club club : clubsFromCSV) {
			for (int i = 0; i < competitionDao.listItems.size(); i++) {
				if (club.getCompetition().getId() == competitionDao.listItems.get(i).getId()) {
					club.setCompetition(competitionDao.listItems.get(i));
				}
			}
			clubDao.create(club);
		}

		jpaConfig.commitTransaction();
		// #endregion

		// #region Transaction des joueurs
		jpaConfig.startTransaction();

		JoueurDao joueurDao = new JoueurDao(jpaConfig.getManager(), logger);
		for (Joueur joueur : joueursFromCSV) {
			for (int i = 0; i < clubDao.listItems.size(); i++) {
				if (joueur.getClubActuel().getId() == clubDao.listItems.get(i).getId()) {
					joueur.setClubActuel(clubDao.listItems.get(i));
				}
			}
			joueurDao.create(joueur);
		}

		jpaConfig.commitTransaction();
		// #endregion

		// #region Transaction des valeurs de marche
		jpaConfig.startTransaction();
		ValeurMarcheDao valeurMarcheDao = new ValeurMarcheDao(jpaConfig.getManager(),
				logger);
		List<ValeurMarche> valeursMarcheToPush = new ArrayList<>();

		for (ValeurMarche valeurMarche : valeursMarcheFromCSV) {
			for (int i = 0; i < joueurDao.listItems.size(); i++) {
				if (valeurMarche.getJoueur().getId() == joueurDao.listItems.get(i).getId()) {
					valeurMarche.setJoueur(joueursFromCSV.get(i));
					valeursMarcheToPush.add(valeurMarche);
				}
			}
		}

		for (ValeurMarche valeurMarche : valeursMarcheToPush) {
			valeurMarcheDao.create(valeurMarche);
		}

		jpaConfig.commitTransaction();
		// #endregion

		// #region Transaction des matchs
		jpaConfig.startTransaction();

		MatchDao matchDao = new MatchDao(jpaConfig.getManager(), logger);
		List<Match> matchesToPush = new ArrayList<>();

		for (Match match : matchesFromCSV) {
			Boolean isDomicileFind = clubDao.listItems.stream()
					.anyMatch(club -> club.getId().equals(match.getDomicile().getId()));
			Boolean isExterieurFind = clubDao.listItems.stream()
					.anyMatch(club -> club.getId().equals(match.getExterieur().getId()));
			Boolean isCompetitionFind = competitionDao.listItems.stream()
					.anyMatch(competition -> competition.getId()
							.equals(match.getCompetition().getId()));

			if (isDomicileFind && isExterieurFind && isCompetitionFind) {
				match.setDomicile(
						clubDao.listItems.stream()
								.filter(club -> club.getId()
										.equals(match.getDomicile().getId()))
								.findFirst().get());

				match.setExterieur(clubDao.listItems.stream()
						.filter(club -> club.getId().equals(match.getExterieur().getId()))
						.findFirst().get());

				match.setCompetition(competitionDao.listItems.stream()
						.filter(competition -> competition.getId()
								.equals(match.getCompetition().getId()))
						.findFirst()
						.get());

				matchesToPush.add(match);
			}

		}

		for (Match match : matchesToPush) {
			matchDao.create(match);
		}

		jpaConfig.commitTransaction();
		// #endregion

		// // #region Transaction des evenements
		// jpaConfig.startTransaction();

		// EvenementDao evenementDao = new EvenementDao(jpaConfig.getManager(), logger);
		// List<Evenement> evenementsToPush = new ArrayList<>();
		// for (Evenement evenement : evenementsFromCSV) {
		// Boolean isMatchFind = matchDao.listItems.stream()
		// .anyMatch(match -> match.getId().equals(evenement.getMatch().getId()));
		// Boolean isClubFind = clubDao.listItems.stream()
		// .anyMatch(club -> club.getId().equals(evenement.getClub().getId()));
		// Boolean isJoueurFind = joueursFromCSV.stream()
		// .anyMatch(joueur -> joueur.getId().equals(evenement.getJoueur().getId()));
		// Boolean isJoueurAssistantFind = joueursFromCSV.stream()
		// .anyMatch(joueur ->
		// joueur.getId().equals(evenement.getJoueurAssistant().getId()));
		// Boolean isJoueurRemplacantFind = joueursFromCSV.stream()
		// .anyMatch(joueur ->
		// joueur.getId().equals(evenement.getJoueurRemplacant().getId()));

		// if (isMatchFind && isClubFind && isJoueurFind) {
		// evenement.setMatch(matchDao.listItems.stream()
		// .filter(match ->
		// match.getId().equals(evenement.getMatch().getId())).findFirst().get());
		// evenement.setClub(clubDao.listItems.stream()
		// .filter(club ->
		// club.getId().equals(evenement.getClub().getId())).findFirst().get());
		// evenement.setJoueur(joueursFromCSV.stream()
		// .filter(joueur ->
		// joueur.getId().equals(evenement.getJoueur().getId())).findFirst().get());
		// evenement.setJoueurAssistant(isJoueurAssistantFind ? joueursFromCSV.stream()
		// .filter(joueur ->
		// joueur.getId().equals(evenement.getJoueurAssistant().getId())).findFirst()
		// .get() : null);
		// evenement.setJoueurRemplacant(isJoueurRemplacantFind ?
		// joueursFromCSV.stream()
		// .filter(joueur ->
		// joueur.getId().equals(evenement.getJoueurRemplacant().getId())).findFirst()
		// .get() : null);
		// evenementsToPush.add(evenement);
		// }
		// }

		// logger.info("Liste des evenements a inserer : " + evenementsToPush.size());

		// for (Evenement evenement : evenementsToPush) {
		// evenementDao.create(evenement);
		// }

		// jpaConfig.commitTransaction();
		// // #endregion

		// #region Transaction des compositions
		jpaConfig.startTransaction();

		CompositionDao compositionDao = new CompositionDao(jpaConfig.getManager(),
				logger);
		List<Composition> compositionsToPush = new ArrayList<>();
		for (Composition composition : compositionsFromCSV) {
			Boolean isClubFind = clubDao.listItems.stream()
					.anyMatch(club -> club.getId().equals(composition.getClub().getId()));
			Boolean isMatchFind = matchDao.listItems.stream()
					.anyMatch(match -> match.getId().equals(composition.getMatch().getId()));
			Boolean isJoueurFind = joueursFromCSV.stream()
					.anyMatch(joueur -> joueur.getId().equals(composition.getJoueur().getId()));

			if (isClubFind && isMatchFind && isJoueurFind) {
				composition.setClub(clubDao.listItems.stream()
						.filter(club -> club.getId().equals(composition.getClub().getId()))
						.findFirst().get());
				composition.setMatch(matchDao.listItems.stream()
						.filter(match -> match.getId().equals(composition.getMatch().getId()))
						.findFirst().get());
				composition.setJoueur(joueursFromCSV.stream()
						.filter(joueur -> joueur.getId()
								.equals(composition.getJoueur().getId()))
						.findFirst().get());
				compositionsToPush.add(composition);
			}
		}

		for (Composition composition : compositionsToPush) {
			compositionDao.create(composition);
		}

		jpaConfig.commitTransaction();

		// #endregion

		// #region Transaction des apparitions
		jpaConfig.startTransaction();
		ApparitionDao apparitionDao = new ApparitionDao(jpaConfig.getManager(),
				logger);
		List<Apparition> apparitionsToPush = new ArrayList<>();

		for (Apparition apparition : apparitionsFromCSV) {
			Boolean isMatchFind = matchDao.listItems.stream()
					.anyMatch(match -> match.getId().equals(apparition.getMatch().getId()));
			Boolean isClubFind = clubDao.listItems.stream()
					.anyMatch(club -> club.getId().equals(apparition.getClubJoueur().getId()));
			Boolean isJoueurFind = joueursFromCSV.stream()
					.anyMatch(joueur -> joueur.getId().equals(apparition.getJoueur().getId()));
			Boolean isCompetitionFind = competitionDao.listItems.stream()
					.anyMatch(competition -> competition.getId()
							.equals(apparition.getCompetition().getId()));

			apparition.setMatch(isMatchFind ? matchDao.listItems.stream()
					.filter(match -> match.getId().equals(apparition.getMatch().getId()))
					.findFirst().get() : null);
			apparition.setClubJoueur(isClubFind ? clubDao.listItems.stream()
					.filter(club -> club.getId().equals(apparition.getClubJoueur().getId()))
					.findFirst().get() : null);
			apparition.setJoueur(isJoueurFind ? joueursFromCSV.stream()
					.filter(joueur -> joueur.getId().equals(apparition.getJoueur().getId()))
					.findFirst().get() : null);
			apparition.setCompetition(isCompetitionFind ? competitionDao.listItems.stream()
					.filter(competition -> competition.getId()
							.equals(apparition.getCompetition().getId()))
					.findFirst().get() : null);

			apparitionsToPush.add(apparition);
		}

		for (Apparition apparition : apparitionsToPush) {
			apparitionDao.create(apparition);
		}

		jpaConfig.commitTransaction();
		// #endregion

		// Fin de l'unite de persistence
		jpaConfig.close();
	}
}