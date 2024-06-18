### Projet Java - Football Club Results

---

1.  ##### <u>Présentation du projet</u>

    Le projet consiste en la lecture de plusieurs fichiers CSV afin de pouvoir mettres leurs données dans une base de données et en faire ensuite la lecture.

    Ce projet est fait avec Java, Maven, JPA, Hibernate et PostgreSQL.

    Afin de meix comprendre l'arborescence du projet, une illustration des dossiers est disponible ci-dessous:

    ##### [<ins>Voir arborescence du projet ici<ins>](docs\Arborescence.md)

2.  ##### <u>Lancement du projet</u>
    Le projet ce compose de deux fichier de lancement:
    - Init: le fichier **"Init.java"** permets de lancer l'extraction des données des fichiers CSV que on peut retrouver dans le dossier **"resources/csv"** afin de les envoyer vers la base de données passer en arguments du fichier **"META-INF/persistance.xml"**.
    - MainApplication: Le fichier **"MainApplication.java"** quand à lui, sert à lancer une interface d'utilisation dans le terminal afin de pouvoir avoir accès au données dans la base.

3.  ##### <u>Gestion de projet</u>
    Enfin pour voir les fichiers UML qui ont servient à faire ce projet, il faudra les voir à la racine du projet, dans le dossier **"conception"**.