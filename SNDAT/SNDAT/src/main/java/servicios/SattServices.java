/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import interfaces.ISatt;
import java.util.*;
import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import dto.*;

/**
 *
 * @author ca.carrillo2369
 */
@Path("/SATT")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SattServices {
    
    @EJB
    private ISatt sattEjb;
    
    @POST
    @Path("verificarTsunami/")
    public List<DatosSismo> verificarTsunami( List<DatosSismo> dS ){    
        for (DatosSismo d : dS) {
            sattEjb.verificarTsunami(d);
        }
       
        return dS;
    }
    
    @GET
    @Path("reportes/")
    public List verReportes(){
        return sattEjb.darReportes();
    }
}
