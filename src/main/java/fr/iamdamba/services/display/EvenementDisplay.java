package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Club;
import fr.iamdamba.entities.Evenement;
import fr.iamdamba.entities.Joueur;
import fr.iamdamba.entities.Match;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.EvenementDao;

public class EvenementDisplay {
    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        EvenementDao dao = new EvenementDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Evenement> query = dao.all();

        System.out.println("\nListe des Evenements: \n");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Evenement n'a été trouve\n");
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
        EvenementDao dao = new EvenementDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Evenement> query = dao.all();

        System.out.println("\nListe des Evenements: \n");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Evenement n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            for (Evenement evenement : query) {
                Joueur joueur = evenement.getJoueur();
                if (joueur.getPaysNaissance().equals(country)) {
                    System.out.println(evenement.toString());
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
        EvenementDao dao = new EvenementDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Evenement> query = dao.one(id);

        System.out.println("\nEvenement correspondant à l'id " + id + ": \n");

        if (query.isEmpty()) {
            System.out.println("Cette Evenement n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            Match match = query.get().getMatch();

            Club club = query.get().getClub();

            Joueur joueur = query.get().getJoueur();

            Joueur joueurAssistant = query.get().getJoueurAssistant();

            Joueur joueurRemplacant = query.get().getJoueurRemplacant();

            System.out.println(query.get().toString());
            System.out.println();

            if (match != null) {
                System.out.println("Match: " + match.toString());
                System.out.println();
            }
            if (club != null) {
                System.out.println("Club: " + club.toString());
                System.out.println();
            }
            if (joueur != null) {
                System.out.println("Joueur: " + joueur.toString());
                System.out.println();
            }
            if (joueurAssistant != null) {
                System.out.println("Joueur Assistant: " + joueurAssistant.toString());
                System.out.println();
            }
            if (joueurRemplacant != null) {
                System.out.println("Joueur Remplacant: " + joueurRemplacant.toString());
                System.out.println();
            }
        }

        // Separateur de ligne
        System.out.println("------------------------------------\n");

        AppModel.jpaConfig.commitTransaction();
    }

}
