package fr.iamdamba.models;

import java.io.IOException;
import java.util.Scanner;

import fr.iamdamba.services.display.ApparitionDisplay;
import fr.iamdamba.services.display.ClubDisplay;
import fr.iamdamba.services.display.CompetitionDisplay;
import fr.iamdamba.services.display.CompositionDisplay;
import fr.iamdamba.services.display.EvenementDisplay;
import fr.iamdamba.services.display.JoueurDisplay;
import fr.iamdamba.services.display.MatchDisplay;
import fr.iamdamba.services.display.ValeurMarcheDisplay;

public class QueryTableOptions {

    public static void competitionOption() throws InterruptedException, IOException {
        Boolean returnBack = false;

        while (returnBack == false) {
            System.out.println("Quel requête pour la table competitions : ");
            System.out.println("0 - Retour en arrière");
            System.out.println("1 - Tout afficher");
            System.out.println("2 - Tout afficher par pays");
            System.out.println("3 - Afficher une competition par son identifiant");
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                Integer choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        returnBack = true;
                        break;
                    case 1:
                        CompetitionDisplay.showAll();
                        break;
                    case 2:
                        System.out.println("Entrer le nom du pays: ");
                        CompetitionDisplay.showAllByCountry(scanner.next());
                        break;
                    case 3:
                        System.out.println("Entrer l'identifiant à afficher : ");
                        CompetitionDisplay.showOne(scanner.next());
                        break;
                    default:
                        System.out.println("Erreur: Veuillez entrer un choix valide");
                        break;
                }
            } else {
                System.out.println("Erreur: La requête n'est pas valide");
            }
        }

    }

    public static void clubOption() throws InterruptedException, IOException {
        Boolean returnBack = false;
        while (returnBack == false) {
            System.out.println("Quel requête pour la table club : ");
            System.out.println("0 - Retour en arrière");
            System.out.println("1 - Tout afficher");
            System.out.println("3 - Afficher un club par son identifiant");

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                Integer choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        returnBack = true;
                        break;
                    case 1:
                        ClubDisplay.showAll();
                        break;
                    case 3:
                        System.out.println("Entrer l'identifiant à afficher : ");
                        ClubDisplay.showOne(scanner.nextInt());
                        break;
                    default:
                        System.out.println("Erreur: Veuillez entrer un choix valide");
                        break;
                }
            } else {
                System.out.println("Erreur: La requête n'est pas valide");
            }
        }
    }

    public static void joueurOption() throws InterruptedException, IOException {
        Boolean returnBack = false;
        while (returnBack == false) {
            System.out.println("Quel requête pour la table joueur : ");
            System.out.println("0 - Retour en arrière");
            System.out.println("1 - Tout afficher");
            System.out.println("2 - Tout afficher par pays de naissance");
            System.out.println("3 - Afficher un joueur par son identifiant");

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                Integer choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        returnBack = true;
                        break;
                    case 1:
                        JoueurDisplay.showAll();
                        break;
                    case 2:
                        System.out.println("Entrer le nom du pays: ");
                        JoueurDisplay.showAllByCountry(scanner.next());
                        break;
                    case 3:
                        System.out.println("Entrer l'identifiant à afficher : ");
                        JoueurDisplay.showOne(scanner.nextInt());
                        break;
                    default:
                        System.out.println("Erreur: Veuillez entrer un choix valide");
                        break;
                }
            } else {
                System.out.println("Erreur: La requête n'est pas valide");
            }
        }
    }

    public static void valeurMarcheOption() throws InterruptedException, IOException {
        Boolean returnBack = false;
        while (returnBack == false) {
            System.out.println("Quel requête pour la table valeur joueur : ");
            System.out.println("0 - Retour en arrière");
            System.out.println("1 - Tout afficher");
            System.out.println("2 - Tout afficher par pays de naissance de joueur");
            System.out.println("3 - Afficher une valeur joueur par son identifiant");
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                Integer choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        returnBack = true;
                        break;
                    case 1:
                        ValeurMarcheDisplay.showAll();
                        break;
                    case 2:
                        System.out.println("Entrer le nom du pays: ");
                        ValeurMarcheDisplay.showAllByCountry(scanner.next());
                        break;
                    case 3:
                        System.out.println("Entrer l'identifiant à afficher : ");
                        ValeurMarcheDisplay.showOne(scanner.nextInt());
                        break;
                    default:
                        System.out.println("Erreur: Veuillez entrer un choix valide");
                        break;
                }
            } else {
                System.out.println("Erreur: La requête n'est pas valide");
            }
        }
    }

    public static void matchOption() throws InterruptedException, IOException {
        Boolean returnBack = false;
        while (returnBack == false) {
            System.out.println("Quel requête pour la table match : ");
            System.out.println("0 - Retour en arrière");
            System.out.println("1 - Tout afficher");
            System.out.println("2 - Tout afficher par saison");
            System.out.println("3 - Afficher un match par son identifiant");
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                Integer choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        returnBack = true;
                        break;
                    case 1:
                        MatchDisplay.showAll();
                        break;
                    case 2:
                        System.out.println("Entrer la saison: ");
                        MatchDisplay.showAllBySeason(scanner.nextInt());
                        break;
                    case 3:
                        System.out.println("Entrer l'identifiant à afficher : ");
                        MatchDisplay.showOne(scanner.nextInt());
                        break;
                    default:
                        System.out.println("Erreur: Veuillez entrer un choix valide");
                        break;
                }
            } else {
                System.out.println("Erreur: La requête n'est pas valide");
            }
        }
    }

    public static void evenementOption() throws InterruptedException, IOException {
        Boolean returnBack = false;
        while (returnBack == false) {
            System.out.println("Quel requête pour la table evenement : ");
            System.out.println("0 - Retour en arrière");
            System.out.println("1 - Tout afficher");
            System.out.println("2 - Tout afficher par pays");
            System.out.println("3 - Afficher un evenement par son identifiant");
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                Integer choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        returnBack = true;
                        break;
                    case 1:
                        EvenementDisplay.showAll();
                        break;
                    case 3:
                        System.out.println("Entrer l'identifiant à afficher : ");
                        EvenementDisplay.showOne(scanner.nextInt());
                        break;
                    default:
                        System.out.println("Erreur: Veuillez entrer un choix valide");
                        break;
                }
            } else {
                System.out.println("Erreur: La requête n'est pas valide");
            }
        }
    }

    public static void compositionOption() throws InterruptedException, IOException {
        Boolean returnBack = false;
        while (returnBack == false) {
            System.out.println("Quel requête pour la table composition : ");
            System.out.println("0 - Retour en arrière");
            System.out.println("1 - Tout afficher");
            System.out.println("2 - Afficher une composition par son identifiant");
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                Integer choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        returnBack = true;
                        break;
                    case 1:
                        CompositionDisplay.showAll();
                        break;
                    case 3:
                        System.out.println("Entrer l'identifiant à afficher : ");
                        CompositionDisplay.showOne(scanner.nextInt());
                        break;
                    default:
                        System.out.println("Erreur: Veuillez entrer un choix valide");
                        break;
                }
            } else {
                System.out.println("Erreur: La requête n'est pas valide");
            }
        }
    }

    public static void apparitionOption() throws InterruptedException, IOException {
        Boolean returnBack = false;
        while (returnBack == false) {
            System.out.println("Quel requête pour la table apparition : ");
            System.out.println("0 - Retour en arrière");
            System.out.println("1 - Tout afficher");
            System.out.println("2 - Afficher une apparition par son identifiant");
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                Integer choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        returnBack = true;
                        break;
                    case 1:
                        ApparitionDisplay.showAll();
                        break;
                    case 2:
                        System.out.println("Entrer l'identifiant à afficher : ");
                        ApparitionDisplay.showOne(scanner.next());
                        break;
                    default:
                        System.out.println("Erreur: Veuillez entrer un choix valide");
                        break;
                }
            } else {
                System.out.println("Erreur: La requête n'est pas valide");
            }
        }
    }
}
