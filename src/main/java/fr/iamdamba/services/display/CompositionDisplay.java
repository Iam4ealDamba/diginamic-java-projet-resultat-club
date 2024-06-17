package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Composition;
import fr.iamdamba.entities.Composition;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.CompositionDao;
import fr.iamdamba.services.CompositionDao;

public class CompositionDisplay {

    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        CompositionDao dao = new CompositionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Composition> query = dao.all();

        System.out.println("\nListe des Compositions: ");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Composition n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.forEach(q -> {
                System.out.println("Id: " + q.getId());
                System.out.println("Number: " + q.getNumber());
                System.out.println("Position: " + q.getPosition());
                System.out.println("Type: " + q.getType());
                System.out.println("Capitaine: " + q.getCapitaineEquipe());
                System.out.println();
            });
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        CompositionDao dao = new CompositionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Composition> query = dao.one(id);

        System.out.println("\nLa Composition correspondant à l'id " + id + ": ");

        if (query.isEmpty()) {
            System.out.println("Cette Composition n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            System.out.println(query.get().toString());
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

}
