package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Competition;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.CompetitionDao;

public class CompetitionDisplay {

    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        CompetitionDao dao = new CompetitionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Competition> query = dao.all();

        System.out.println("\nListe des competitions: ");

        if (query.stream().count() == 0) {
            System.out.println("Aucune competition n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.forEach(q -> {
                System.out.println(q.getId() + " - " + q.getName());
                System.out.println();
            });
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        CompetitionDao dao = new CompetitionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Competition> query = dao.one(id);

        System.out.println("\nLa competition correspondant à l'id " + id + ": ");

        if (query.isEmpty()) {
            System.out.println("Cette competition n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            System.out.println(query.get().toString());
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

}
