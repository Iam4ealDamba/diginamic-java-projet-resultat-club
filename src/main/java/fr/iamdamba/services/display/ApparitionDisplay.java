package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.Club;
import fr.iamdamba.entities.Competition;
import fr.iamdamba.entities.Joueur;
import fr.iamdamba.entities.Match;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.ApparitionDao;
import fr.iamdamba.services.MatchDao;

public class ApparitionDisplay {
    /** Affiche la liste des apparitions */
    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        ApparitionDao dao = new ApparitionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Apparition> query = dao.all();

        System.out.println("\nListe des Apparitions: \n");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Apparition n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.forEach(q -> {
                System.out.println(q.toString());
                System.out.println();
            });
            System.out.println();
        }

        // Separateur de ligne
        System.out.println("------------------------------------\n");

        AppModel.jpaConfig.commitTransaction();
    }

    /** Affiche l'apparition correspondant à l'id */
    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        ApparitionDao dao = new ApparitionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Apparition> query = dao.one(id);

        System.out.println("\nApparition correspondant à l'id " + id + ": \n");

        if (query.isEmpty()) {
            System.out.println("Cette Apparition n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            Match match = query.get().getMatch();
            Joueur joueur = query.get().getJoueur();
            Club clubJoueur = query.get().getClubJoueur();
            Club clubActuel = query.get().getClubJoueurActuel();
            Competition competition = query.get().getCompetition();

            System.out.println(query.get().toString());

            if (match != null) {
                System.out.println("Match: " + match.toString());
                System.out.println();
            }

            if (joueur != null) {
                System.out.println("Joueur: " + joueur.toString());
                System.out.println();
            }

            if (clubJoueur != null) {
                System.out.println("Club Joueur: " + clubJoueur.toString());
                System.out.println();
            }

            if (clubActuel != null) {
                System.out.println("Club Actuel: " + clubActuel.toString());
                System.out.println();
            }

            if (competition != null) {
                System.out.println("Competition: " + competition.toString());
                System.out.println();
            }

            // Separateur de ligne
            System.out.println("------------------------------------\n");
        }
        AppModel.jpaConfig.commitTransaction();
    }

}
