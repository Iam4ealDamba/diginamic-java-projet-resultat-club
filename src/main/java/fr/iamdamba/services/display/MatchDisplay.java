package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Match;
import fr.iamdamba.entities.Match;
import fr.iamdamba.entities.Match;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.MatchDao;
import fr.iamdamba.services.MatchDao;
import fr.iamdamba.services.MatchDao;

public class MatchDisplay {

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

    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        MatchDao dao = new MatchDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Match> query = dao.one(id);

        System.out.println("\nLa Match correspondant à l'id " + id + ": ");

        if (query.isEmpty()) {
            System.out.println("Cette Match n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            System.out.println(query.get().toString());
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

}
