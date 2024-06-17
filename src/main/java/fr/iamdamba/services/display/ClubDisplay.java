package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Club;
import fr.iamdamba.entities.Club;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.ClubDao;
import fr.iamdamba.services.ClubDao;

public class ClubDisplay {

    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        ClubDao dao = new ClubDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Club> query = dao.all();

        System.out.println("\nListe des Clubs: ");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Club n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.forEach(q -> {
                System.out.println("Id: " + q.getId());
                System.out.println("Nom: " + q.getNom());
                System.out.println("Lien: " + q.getUrl());
                System.out.println("Code Club: " + q.getCodeClub());
                System.out.println("Dernière Saison: " + q.getDerniereSaison());
                System.out.println();
            });
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        ClubDao dao = new ClubDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Club> query = dao.one(id);

        System.out.println("\nLa Club correspondant à l'id " + id + ": ");

        if (query.isEmpty()) {
            System.out.println("Cette Club n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            System.out.println(query.get().toString());
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

}
