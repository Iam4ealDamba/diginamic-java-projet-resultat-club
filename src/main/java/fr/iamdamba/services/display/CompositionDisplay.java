package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Club;
import fr.iamdamba.entities.Composition;
import fr.iamdamba.entities.Joueur;
import fr.iamdamba.entities.Match;
import fr.iamdamba.entities.Composition;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.CompositionDao;
import fr.iamdamba.services.CompositionDao;

public class CompositionDisplay {
    /** Affiche la liste des Compositions */
    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        CompositionDao dao = new CompositionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Composition> query = dao.all();

        System.out.println("\nListe des Compositions: \n");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Composition n'a été trouve\n");
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

    /** Affiche la Composition correspondant à l'id */
    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        CompositionDao dao = new CompositionDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Composition> query = dao.one(id);

        System.out.println("\nComposition correspondant à l'id " + id + ": \n");

        if (query.isEmpty()) {
            System.out.println("Composition n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            Club club = query.get().getClub();
            Match match = query.get().getMatch();
            Joueur joueur = query.get().getJoueur();

            System.out.println(query.get().toString());
            System.out.println();

            if (club != null) {
                System.out.println("Club: " + club.toString());
                System.out.println();
            }

            if (match != null) {
                System.out.println("Match: " + match.toString());
                System.out.println();
            }

            if (joueur != null) {
                System.out.println("Joueur: " + joueur.toString());
                System.out.println();
            }
        }

        // Separateur de ligne
        System.out.println("------------------------------------\n");

        AppModel.jpaConfig.commitTransaction();
    }

}
