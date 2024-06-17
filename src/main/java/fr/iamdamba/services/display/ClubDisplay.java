package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Apparition;
import fr.iamdamba.entities.Club;
import fr.iamdamba.entities.Competition;
import fr.iamdamba.entities.Composition;
import fr.iamdamba.entities.Joueur;
import fr.iamdamba.entities.Club;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.ClubDao;
import fr.iamdamba.services.ClubDao;

public class ClubDisplay {

    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        ClubDao dao = new ClubDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Club> query = dao.all();

        System.out.println("\nListe des Clubs: \n");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Club n'a été trouve\n");
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

    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        ClubDao dao = new ClubDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Club> query = dao.one(id);

        System.out.println("\nClub correspondant à l'id " + id + ": \n");

        if (query.isEmpty()) {
            System.out.println("Club n'existe pas\n");
        } else {
            Competition competition = query.get().getCompetition();
            List<Joueur> joueurs = query.get().getJoueurs();
            List<Apparition> apparitions = query.get().getApparitions();
            List<Composition> compositions = query.get().getCompositions();

            System.out.println(query.get().toString());
            System.out.println();

            if (competition != null) {
                System.out.println("Competition: " + competition.toString());
                System.out.println();
            }

            if (joueurs != null) {
                System.out.println("Liste des joueurs: ");
                joueurs.forEach(j -> System.out.println(j.toString() + "\n"));
                System.out.println();
            }

            if (apparitions != null) {
                System.out.println("Liste des apparitions: ");
                apparitions.forEach(a -> System.out.println(a.toString() + "\n"));
                System.out.println();
            }

            if (compositions != null) {
                System.out.println("Liste des compositions: ");
                compositions.forEach(c -> System.out.println(c.toString() + "\n"));
                System.out.println();
            }

        }

        // Separateur de ligne
        System.out.println("------------------------------------\n");

        AppModel.jpaConfig.commitTransaction();
    }

}
