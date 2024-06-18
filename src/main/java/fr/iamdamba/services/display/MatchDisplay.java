package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.Club;
import fr.iamdamba.entities.Competition;
import fr.iamdamba.entities.Composition;
import fr.iamdamba.entities.Match;
import fr.iamdamba.entities.Match;
import fr.iamdamba.entities.Match;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.MatchDao;
import fr.iamdamba.services.MatchDao;
import fr.iamdamba.services.MatchDao;

public class MatchDisplay {
    /** Affiche la liste des Matchs */
    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        MatchDao dao = new MatchDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Match> query = dao.all();

        System.out.println("\nListe des Matchs: ");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Match n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.forEach(q -> {
                System.out.println("Id: " + q.getId());
                System.out.println("Arbitre: " + q.getArbitre());
                System.out.println("Entraineur Domicile: " + q.getEntraineurDomicile());
                System.out.println("Entraineur Exterieur: " + q.getEntraineurExterieur());
                System.out.println("Score: " + q.getScore());
                System.out.println("Stade: " + q.getStade());
                System.out.println("Tour: " + q.getTour());
                System.out.println("Saison: " + q.getSaison());
                System.out.println();
            });
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

    /** Affiche la liste des Matchs d'une saison */
    public static void showAllBySeason(Integer saison) {
        AppModel.jpaConfig.startTransaction();
        MatchDao dao = new MatchDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Match> query = dao.all();

        System.out.println("\nListe des Matchs: \n");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Match n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.stream().filter(q -> q.getSaison().equals(saison)).forEach(q -> {
                System.out.println(q.toString());
                System.out.println();
            });
            System.out.println();
        }

        // Separateur de ligne
        System.out.println("------------------------------------\n");

        AppModel.jpaConfig.commitTransaction();
    }

    /** Affiche la Match correspondant à l'id */
    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        MatchDao dao = new MatchDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Match> query = dao.one(id);

        System.out.println("\nLa Match correspondant à l'id " + id + ": \n");

        if (query.isEmpty()) {
            System.out.println("Cette Match n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            Competition competition = query.get().getCompetition();
            Club domicile = query.get().getDomicile();
            Club exterieur = query.get().getExterieur();
            List<Apparition> apparitions = query.get().getApparitions();
            List<Composition> compositions = query.get().getCompositions();

            System.out.println(query.get().toString());
            System.out.println();

            if (competition != null) {
                System.out.println("Competition: " + competition.toString());
                System.out.println();
            }

            if (domicile != null) {
                System.out.println("Domicile: " + domicile.toString());
                System.out.println();
            }

            if (exterieur != null) {
                System.out.println("Exterieur: " + exterieur.toString());
                System.out.println();
            }

            if (apparitions != null) {
                System.out.println("Liste des apparitions: ");
                apparitions.forEach(q -> {
                    System.out.println(q.toString());
                });
                System.out.println();
            }

            // Separateur de ligne
            System.out.println("------------------------------------\n");

            AppModel.jpaConfig.commitTransaction();
        }
    }
}
