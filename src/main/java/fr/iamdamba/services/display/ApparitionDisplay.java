package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.ApparitionDao;

public class ApparitionDisplay {

    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        ApparitionDao dao = new ApparitionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Apparition> query = dao.all();

        System.out.println("\nListe des Apparitions: ");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Apparition n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.forEach(q -> {
                System.out.println("Id: " + q.getId());
                System.out.println("Date: " + q.getDate());
                System.out.println("Nombre Assistance: " + q.getNbAssistance());
                System.out.println("Nombre But: " + q.getNbBut());
                System.out.println("Nombre Carton Jaune: " + q.getNbCartonJaune());
                System.out.println("Nombre Carton Rouge: " + q.getNbCartonRouge());
                System.out.println();
            });
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        ApparitionDao dao = new ApparitionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Apparition> query = dao.one(id);

        System.out.println("\nLa Apparition correspondant à l'id " + id + ": ");

        if (query.isEmpty()) {
            System.out.println("Cette Apparition n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            System.out.println(query.get().toString());
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

}
