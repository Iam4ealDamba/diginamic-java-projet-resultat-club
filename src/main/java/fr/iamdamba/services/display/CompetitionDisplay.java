package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Club;
import fr.iamdamba.entities.Competition;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.CompetitionDao;

public class CompetitionDisplay {
    /** Affiche la liste des competitions */
    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        CompetitionDao dao = new CompetitionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Competition> query = dao.all();

        System.out.println("\nListe des competitions: \n");

        if (query.stream().count() == 0) {
            System.out.println("Aucune competition n'a été trouve\n");
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

    /** Affiche la liste des competitions d'un pays */
    public static void showAllByCountry(String country) {
        AppModel.jpaConfig.startTransaction();
        CompetitionDao dao = new CompetitionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Competition> query = dao.all();

        System.out.println("\nListe des competitions: \n");

        if (query.stream().count() == 0) {
            System.out.println("Aucune competition n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.stream().filter(q -> q.getNomPays().equals(country)).forEach(q -> {
                System.out.println(q.toString());
                System.out.println();
            });
            System.out.println();
        }

        // Separateur de ligne
        System.out.println("------------------------------------\n");

        AppModel.jpaConfig.commitTransaction();
    }

    /** Affiche la competition correspondant à l'id */
    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        CompetitionDao dao = new CompetitionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Competition> query = dao.one(id);

        System.out.println("\ncompetition correspondant à l'id " + id + ": \n");

        if (query.isEmpty()) {
            System.out.println("Cette competition n'existe pas\n");
        } else {
            List<Club> listClub = query.get().getClubs();

            System.out.println(query.get().toString());

            if (listClub != null) {
                System.out.println("\nListe des clubs: \n");
                listClub.forEach(lc -> System.out.println(lc.toString() + "\n"));
                System.out.println();
            }

        }

        // Separateur de ligne
        System.out.println("------------------------------------\n");

        AppModel.jpaConfig.commitTransaction();
    }

}
