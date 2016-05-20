/*
 * Esta parte se ha tomado de: http://javanotepad.blogspot.com/2007/05/jpa-entitymanagerfactory-in-web.html
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mauricio
 */
public class PersistenceManager {
    
    private static final Logger log= Logger.getLogger( PersistenceManager.class.getName() );
    
    public static final boolean DEBUG = true;

    private static final PersistenceManager singleton = new PersistenceManager();

    protected EntityManagerFactory emf;

    public static PersistenceManager getInstance() {

        return singleton;
    }
    

    private PersistenceManager() {
    }

    public EntityManagerFactory getEntityManagerFactory() {

        if (emf == null) {
            createEntityManagerFactory();
        }
        return emf;
    }

    public void closeEntityManagerFactory() {

        if (emf != null) {
            emf.close();
            emf = null;
            if (DEBUG) {
                log.log(Level.FINER, "n*** Persistence finished at " + new java.util.Date());
            }
        }
    }

    protected void createEntityManagerFactory() {

        this.emf = Persistence.createEntityManagerFactory("mongoPU", System.getProperties());
        if (DEBUG) {
            log.log(Level.FINE,"n*** Persistence started at " + new java.util.Date());
        }
    }

}
