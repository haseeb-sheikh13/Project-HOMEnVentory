package database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 687159
 */
public class DBUtil {
    private static final EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("HOMEnVentoryPU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
