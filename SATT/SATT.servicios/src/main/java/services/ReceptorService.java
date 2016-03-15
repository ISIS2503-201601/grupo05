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
     * POST que se recibe la señal enviada por un sensor
     * @param señal señal enviada por un sensor
     * @return señal recibida
     */
    @POST
    @Path("enviar/")
 
    public List<Señal> recibirSeñal(List<Señal> señalRecibida) 
    {
        for(Señal s : señalRecibida)
        {
            receptorEjb.recibirSeñal(s);
            entityManager.getTransaction().begin();
            entityManager.persist(s);
            entityManager.getTransaction().commit();
            entityManager.refresh(s);
        }
        
        return señalRecibida;
    }
   
    /**
     * GET que retorna la información sobre las señales recibidas que está
     * almacenada en el sistema
     * @return información disponible sobre señales recibidas anteriormente
     */
    @GET
    @Path("mostrar/")
    public Response darSeñalesHistoricas()
    {
//        return receptorEjb.darSeñales();
        Query q = entityManager.createQuery("SELECT u FROM Señal u");
        List<Señal> señales = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(señales).build();
    }
 
}
