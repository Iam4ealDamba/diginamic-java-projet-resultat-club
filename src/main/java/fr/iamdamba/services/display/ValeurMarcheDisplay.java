package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Joueur;
import fr.iamdamba.entities.ValeurMarche;
import fr.iamdamba.entities.ValeurMarche;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.ValeurMarcheDao;
import fr.iamdamba.services.ValeurMarcheDao;

public class ValeurMarcheDisplay {

    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        ValeurMarcheDao dao = new ValeurMarcheDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<ValeurMarche> query = dao.all();

        System.out.println("\nListe des ValeurMarches: \n");

        if (query.stream().count() == 0) {
            System.out.println("Aucune ValeurMarche n'a été trouve\n");
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

    public static void showAllByCountry(String country) {
        AppModel.jpaConfig.startTransaction();
        ValeurMarcheDao dao = new ValeurMarcheDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<ValeurMarche> query = dao.all();

        System.out.println("\nListe des ValeurMarches: \n");

        if (query.stream().count() == 0) {
            System.out.println("Aucune ValeurMarche n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            for (ValeurMarche valeurMarche : query) {
                Joueur joueur = valeurMarche.getJoueur();

                if (joueur.getPaysNaissance().equals(country)) {
                    System.out.println(valeurMarche.toString());
                    System.out.println();
                }
            }
            System.out.println();
        }

        // Separateur de ligne
        System.out.println("------------------------------------\n");

        AppModel.jpaConfig.commitTransaction();
    }

    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        ValeurMarcheDao dao = new ValeurMarcheDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<ValeurMarche> query = dao.one(id);

        System.out.println("\nValeurMarche correspondant à l'id " + id + ": \n");

        if (query.isEmpty()) {
            System.out.println("Cette ValeurMarche n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            ValeurMarche ValeurMarche = query.get();

            System.out.println(query.get().toString());
            System.out.println();

            if (ValeurMarche != null) {
                System.out.println("ValeurMarche: " + ValeurMarche.toString());
                System.out.println();
            }
        }

        // Separateur de ligne
        System.out.println("------------------------------------\n");

        AppModel.jpaConfig.commitTransaction();
    }

}
