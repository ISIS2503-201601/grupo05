package services;

import dto.EventoSismico;
import dto.Reporte;
import dto.Señal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logica.ejb.PersistenceManager;
import logica.interfaces.IServicioReceptorMockLocal;    
import logica.interfaces.IServicioReporteMockLocal;
 
@Path("/reportes")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReporteService {
    
//    @PersistenceContext(unitName = "mongoPU")
    EntityManager entityManager;

    @PostConstruct
    public void init() {
        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Referencia al Ejb para el servicio reporte
     */
    @EJB
    private IServicioReporteMockLocal reporteEjb;
   
    /**
     * Referencia al Ejb para el servicio reporte
     */
    @EJB
    private IServicioReceptorMockLocal receptorEjb;    
        
    /**
     * POST que se recibe los parámetros de evento sísmico, guarda
     * el evento y retorna un reporte del mismo.
     * @param evento parámetros del evento sísmico
     * @return reporte del evento sísmico
     */
    @POST
    @Path("enviar/")
    
    public Reporte enviarEventoSismico(List<EventoSismico> evento) 
    {   
        EventoSismico temp = new EventoSismico();
        
        for(EventoSismico es : evento)
        {
            temp = es;
        }
        
        reporteEjb.recibirEventoSismico(temp); 
        Señal señal = receptorEjb.buscarUltimoRegistroSensorCercano(temp); 
        Reporte r = reporteEjb.generarReporteDeEvento(temp, señal);
        entityManager.getTransaction().begin();
        entityManager.persist(r);
        entityManager.getTransaction().commit();
        entityManager.refresh(r);
        return r;        
    }
   
    /**
     * GET que retorna la información sobre los eventos sismiscos
     * almacenada en el sistema.
     * @return información disponible sobre eventos sismiscos anteriores
     */
    @GET
    @Path("mostrar/")
    public ArrayList<Reporte> darReportesHistoricos() 
    {
        return reporteEjb.darReportesHistoricos();
        //return reporteEjb.darReportesHistoricos();
    }
 
}

