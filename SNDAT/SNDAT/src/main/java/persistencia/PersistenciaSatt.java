/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import interfaces.IPersistenciaSatt;
import java.util.*;

/**
 *
 * @author ca.carrillo2369
 */
public class PersistenciaSatt implements IPersistenciaSatt {
    
    private ArrayList<String> reportes;

    public PersistenciaSatt(){
        reportes = new ArrayList<String>();
    }
    
    @Override
    public void create(Object obj) throws Exception {
        reportes.add((String)obj);
    }

//    @Override
//    public void update(Object obj) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void delete(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findAll( ) {
        return reportes;
    }

    @Override
    public Object findById(Class c, Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
