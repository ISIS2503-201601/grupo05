package services;

import dto.EventoSismico;
import dto.Reporte;
import dto.Señal;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logica.interfaces.IServicioReceptorMockLocal;
import logica.interfaces.IServicioReporteMockLocal;
 
@Path("/receptor")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceptorService {
 
    /**
     * Referencia al Ejb para el servicio reporte
     */
    @EJB
    private IServicioReceptorMockLocal receptorEjb;
   
    /**
     * POST que se recibe la señal enviada por un sensor
     * @param señal señal enviada por un sensor
     * @return señal recibida
     */
    @POST
    @Path("enviar/")
 
    public Señal recibirSeñal(Señal señalRecibida) {
        receptorEjb.recibirSeñal(señalRecibida);
        
        return señalRecibida;
    }
   
    /**
     * GET que retorna la información sobre las señales recibidas que está
     * almacenada en el sistema
     * @return información disponible sobre señales recibidas anteriormente
     */
    @GET
    @Path("mostrar/")
    public ArrayList<Señal> darSeñalesHistoricas()
    {
        return receptorEjb.darSeñales();
    }
 
}
