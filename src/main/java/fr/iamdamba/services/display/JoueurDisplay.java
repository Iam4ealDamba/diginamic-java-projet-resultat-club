package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Club;
import fr.iamdamba.entities.Joueur;
import fr.iamdamba.entities.ValeurMarche;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.JoueurDao;

public class JoueurDisplay {
    /** Affiche la liste des joueurs */
    public static void showAll() {
        AppModel.jpaConfig.startTransaction();
        JoueurDao dao = new JoueurDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Joueur> query = dao.all();

        System.out.println("\nListe des Joueurs: ");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Joueur n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.forEach(q -> {
                System.out.println("Id: " + q.getId());
                System.out.println("Code: " + q.getCode());
                System.out.println("Prenom: " + q.getPrenom());
                System.out.println("Nom: " + q.getNom());
                System.out.println("Image URL: " + q.getImageUrl());
                System.out.println("Pays Naissance: " + q.getPaysNaissance());
                System.out.println("Pays Residence: " + q.getPaysResidence());
                System.out.println("Pied Fort: " + q.getPied());
                System.out.println("Position: " + q.getPosition());
                System.out.println("Position Secondaire: " + q.getPositionSecondaire());
                System.out.println("Nom Agent: " + q.getNomAgent());
                System.out.println();
            });
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

    /** Affiche la liste des joueurs d'un pays */
    public static void showAllByCountry(String country) {
        AppModel.jpaConfig.startTransaction();
        JoueurDao dao = new JoueurDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        List<Joueur> query = dao.all();

        System.out.println("\nListe des Joueurs: \n");

        if (query.stream().count() == 0) {
            System.out.println("Aucune Joueur n'a été trouve\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            query.stream().filter(q -> q.getPaysNaissance().equals(country)).forEach(q -> {
                System.out.println(q.toString());
                System.out.println();
            });
            System.out.println();
        }

        // Separateur de ligne
        System.out.println("------------------------------------\n");

        AppModel.jpaConfig.commitTransaction();
    }

    /** Affiche la Joueur correspondant à l'id */
    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        JoueurDao dao = new JoueurDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Joueur> query = dao.one(id);

        System.out.println("\nLa Joueur correspondant à l'id " + id + ": \n");

        if (query.isEmpty()) {
            System.out.println("Cette Joueur n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            Club clubActuel = query.get().getClubActuel();

            List<ValeurMarche> valeurMarche = query.get().getValeurMarche();

            System.out.println(query.get().toString());
            System.out.println();

            if (clubActuel != null) {
                System.out.println("Club Actuel: " + clubActuel.toString() + "\n");
                System.out.println();
            }

            if (valeurMarche != null) {
                System.out.println("Valeur Marche: ");
                valeurMarche.forEach(q -> {
                    System.out.println(q.toString() + "\n");
                    System.out.println();
                });
            }
        }

        // Separateur de ligne
        System.out.println("------------------------------------\n");

        AppModel.jpaConfig.commitTransaction();
    }

}
