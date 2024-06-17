package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.ValeurMarche;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.ValeurMarcheDao;

public class ValeurMarcheDisplay {

    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        ValeurMarcheDao dao = new ValeurMarcheDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<ValeurMarche> query = dao.all();

        System.out.println("\nListe des ValeurMarches: ");

        if (query.stream().count() == 0) {
            System.out.println("Aucune ValeurMarche n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.forEach(q -> {
                System.out.println("Id: " + q.getId());
                System.out.println("Dernière Saison: " + q.getDerniereSaison());
                System.out.println("Valeur: " + q.getValeur());
                System.out.println("Date: " + q.getDate());
                System.out.println("Date Heure: " + q.getDateHeure());
                System.out.println("Date Semaine: " + q.getDateSemaine());
                System.out.println();
            });
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        ValeurMarcheDao dao = new ValeurMarcheDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<ValeurMarche> query = dao.one(id);

        System.out.println("\nLa ValeurMarche correspondant à l'id " + id + ": ");

        if (query.isEmpty()) {
            System.out.println("Cette ValeurMarche n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            System.out.println(query.get().toString());
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

}
