package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Evenement;
import fr.iamdamba.entities.Evenement;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.EvenementDao;
import fr.iamdamba.services.EvenementDao;

public class EvenementDisplay {
    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        EvenementDao dao = new EvenementDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Evenement> query = dao.all();

        System.out.println("\nListe des Evenements: ");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Evenement n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.forEach(q -> {
                System.out.println("Id: " + q.getId());
                System.out.println("Description: " + q.getDescription());
                System.out.println("Type: " + q.getType());
                System.out.println("Minutes: " + q.getMinutes());
                System.out.println("Date: " + q.getDate());
                System.out.println();
            });
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        EvenementDao dao = new EvenementDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Evenement> query = dao.one(id);

        System.out.println("\nLa Evenement correspondant à l'id " + id + ": ");

        if (query.isEmpty()) {
            System.out.println("Cette Evenement n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            System.out.println(query.get().toString());
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

}
