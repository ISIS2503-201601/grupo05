package services;

import dto.EventoSismico;
import dto.Reporte;
import dto.Señal;
import java.util.ArrayList;
import java.util.List;

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
 
@Path("/reportes")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReporteService {
 
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
        Reporte reporteGenerado = null;
        for(EventoSismico es : evento)
        {
             reporteEjb.recibirEventoSismico(es);
             Señal señal = receptorEjb.buscarUltimoRegistroSensorCercano(es); 
             reporteGenerado = reporteEjb.generarReporteDeEvento(es, señal);
        }
        
        return reporteGenerado;
    }
   
    /**
     * GET que retorna la información sobre los eventos sismiscos
     * almacenada en el sistema.
     * @return información disponible sobre eventos sismiscos anteriores
     */
    @GET
    @Path("mostrar/")
    public ArrayList<EventoSismico> darEventosHistoricos() 
    {
        return reporteEjb.darEventosHistoricos();

    }
 
}

