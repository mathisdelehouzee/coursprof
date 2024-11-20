package umons.fpms.ig19;

import java.sql.Connection;
import java.sql.Statement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Suite de tests regroupant DataAccessTest, ProfTest, et CoursTest.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({DataAccessTest.class, ProfTest.class, CoursTest.class})
public class Ig19Suite {

    /**
     * Méthode appelée avant l'exécution de la suite de tests.
     * Utilisée pour effectuer des configurations globales si nécessaire.
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Suite.BeforeClass.setUpClass");
        // Ajoutez ici toute logique d'initialisation globale si besoin.
    }

    /**
     * Méthode appelée après l'exécution de la suite de tests.
     * Utilisée ici pour supprimer le schéma utilisé par les tests.
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("Suite.AfterClass.tearDownClass");
        try (Connection con = DataAccess.connect();
             Statement stm = con.createStatement()) {
            // Supprime le schéma utilisé par les tests si présent.
            stm.execute("DROP SCHEMA IF EXISTS " + DataAccess.DBNAME + ";");
            System.out.println("SCHEMA '" + DataAccess.DBNAME + "' was DELETED.");
        } catch (Exception e) {
            // Capture et affiche toute erreur pendant la suppression du schéma.
            System.err.println("Error during schema deletion: " + e.getMessage());
        }
    }
}
