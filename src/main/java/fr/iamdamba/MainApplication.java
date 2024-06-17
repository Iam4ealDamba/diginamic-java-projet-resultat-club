package fr.iamdamba;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iamdamba.configs.JpaConfig;
import fr.iamdamba.models.AppModel;

public class MainApplication {
    public static void main(String[] args) throws InterruptedException, IOException {
        // #region Initialisation Variables de l'application
        Logger logger = LoggerFactory.getLogger(MainApplication.class);
        JpaConfig jpaConfig = new JpaConfig("diginamic-resultat-club", logger);
        AppModel application = new AppModel(logger, jpaConfig);

        // #endregion

        // Lancement de l'application
        application.startApp();

        // Fermeture de l'unite de persistance
        jpaConfig.close();
    }
}
