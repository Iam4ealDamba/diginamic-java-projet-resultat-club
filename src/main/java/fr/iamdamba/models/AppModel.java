package fr.iamdamba.models;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;

import fr.iamdamba.configs.JpaConfig;

public class AppModel {
    /** Variable de l'application pour le logger */
    public static Logger logger;
    /** Variable de l'application pour la configuration de l'unite e persistance */
    public static JpaConfig jpaConfig;

    /** Variable de l'application pour la fermeture */
    Boolean isAppClose;

    /** Constructeur */
    public AppModel(Logger logger, JpaConfig jpaConfig) {
        this.isAppClose = false;
        this.logger = logger;
        this.jpaConfig = jpaConfig;
    }

    /**
     * Nettoyer le terminal de l'application
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public static void clearScreen() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    /**
     * Lancement de l'application
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void startApp() throws InterruptedException, IOException {
        // Nettoyage de l'ecran
        clearScreen();

        // Demarrage de l application
        System.out.println("||||||||||||||| Football Club Resultat |||||||||||||||");
        System.out.println();

        // Debut de la boucle principale
        while (this.isAppClose == false) {
            System.out.println("Veuillez choisir une option en dessous : ");
            System.out.println("1 - Choisir une table");
            System.out.println("2 - Quitter l'application");
            System.out.println();
            System.out.println("Votre choix : ");

            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                Integer choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        try {
                            this.chooseTable();
                        } catch (InterruptedException | IOException e) {
                            logger.error("Erreur: Impossible de choisir une table: \n", e);
                        }
                        break;
                    case 2:
                        System.out.println("Fermeture de l'application...");
                        this.isAppClose = true;
                        scanner.close();
                        break;
                    default:
                        System.out.println("Erreur: Cette option n'existe pas.\n");
                        break;
                }
            } else {
                System.out.println("Erreur: Veuillez entrer un nombre valide.\n");
            }
        }
    }

    /**
     * Choisir la table à requêter ou retourner au menu principal
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void chooseTable() throws InterruptedException, IOException {
        Boolean returnBack = false;

        // Nettoyage de l'ecran
        clearScreen();

        while (returnBack == false) {
            System.out.println("Veuillez choisir une des tables suivantes : ");
            System.out.println("0 - Revenir au menu principal");
            System.out.println("1 - Competition");
            System.out.println("2 - Club");
            System.out.println("3 - Joueur");
            System.out.println("4 - Valeur Joueur");
            System.out.println("5 - Match");
            System.out.println("6 - Évenement");
            System.out.println("7 - Composition");
            System.out.println("8 - Apparition");
            System.out.println();
            System.out.println("Votre choix : ");

            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                Integer choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        QueryTableOptions.competitionOption();
                        break;
                    case 2:
                        QueryTableOptions.clubOption();
                        break;
                    case 3:
                        QueryTableOptions.joueurOption();
                        break;
                    case 4:
                        QueryTableOptions.valeurMarcheOption();
                        break;
                    case 5:
                        QueryTableOptions.matchOption();
                        break;
                    case 6:
                        QueryTableOptions.evenementOption();
                        break;
                    case 7:
                        QueryTableOptions.competitionOption();
                        break;
                    case 8:
                        QueryTableOptions.apparitionOption();
                        break;
                    case 0:
                        returnBack = true;
                        clearScreen();
                        break;
                    default:
                        System.out.println("Erreur: Cette option n'existe pas.\n");
                        break;
                }
            } else {
                System.out.println("Erreur: Veuillez entrer un nombre valide.\n");
            }
        }
    }

}
