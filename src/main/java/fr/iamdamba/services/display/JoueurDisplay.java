package fr.iamdamba.services.display;

import java.util.List;
import java.util.Optional;

import fr.iamdamba.entities.Joueur;
import fr.iamdamba.models.AppModel;
import fr.iamdamba.services.JoueurDao;

public class JoueurDisplay {
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

    public static void showOne(Object id) {
        AppModel.jpaConfig.startTransaction();
        JoueurDao dao = new JoueurDao(AppModel.jpaConfig.getManager(), AppModel.logger);
        Optional<Joueur> query = dao.one(id);

        System.out.println("\nLa Joueur correspondant à l'id " + id + ": ");

        if (query.isEmpty()) {
            System.out.println("Cette Joueur n'existe pas\n");
            AppModel.jpaConfig.commitTransaction();
        } else {
            System.out.println(query.get().toString());
            System.out.println();
        }
        AppModel.jpaConfig.commitTransaction();
    }

}
