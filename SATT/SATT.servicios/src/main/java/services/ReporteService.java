package services;

import dto.EventoSismico;
import dto.Reporte;
import dto.Señal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logica.ejb.PersistenceManager;
import logica.interfaces.IServicioReceptorMockLocal;    
import logica.interfaces.IServicioReporteMockLocal;
 
@Path("/reportes")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReporteService {

    
    private static final Logger log= Logger.getLogger( ReporteService.class.getName() );
//    @PersistenceContext(unitName = "mongoPU")
    EntityManager entityManager;

    @PostConstruct
    public void init() {
        try 
        {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        }
        
        catch (Exception e) 
        {
            log.log(Level.SEVERE, e.getMessage());
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
        
        Query q = entityManager.createQuery("SELECT u FROM Señal u");
        List<Señal> señales = q.getResultList();
        for(Señal seña : señales)
        {
            receptorEjb.añadirSeñal(seña);
        }
        
        Señal señal = receptorEjb.buscarUltimoRegistroSensorCercano(temp);
        
        temp.setSeñalCercana(señal);
        
        entityManager.getTransaction().begin();
        entityManager.persist(temp);
        entityManager.getTransaction().commit();
        entityManager.refresh(temp);
        
        Query p = entityManager.createQuery("SELECT u FROM Reporte u ORDER BY u.id DESC");
        
        List<Reporte> reportes = p.getResultList();
        long contador = 0;
        if( !p.getResultList().isEmpty())
        {
          contador = reportes.get(0).getId();
        }
            
        contador++;
        
        Reporte r = reporteEjb.generarReporteDeEvento(temp, señal, contador);
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
    public Response darReportesHistoricos() 
    {   
        Query q = entityManager.createQuery("SELECT u FROM Reporte u");
        List<Reporte> reportes = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(reportes).build();
    }
 
}

