package logica.interfaces;


import java.util.ArrayList;
import javax.ejb.Local;
import dto.Reporte;
import dto.EventoSismico;
import dto.Señal;

/**
 * Mock que ayuda en la prueba del servicio reporte, el cual ofrece:
 *      Un POST en el que recibe los parámetros de un evento sísmico y retorna el reporte del mismo
 *      Un GET en el que despliega los datos qe se tengan de eventos sísmicos anteriores.
 */
@Local
public interface IServicioReporteMockLocal
{

    /**
     * Recibe los parámetros de un evento sísmico y los guarda
     * @param evento objeto que contiene los parámetros del evento sísmico
     */
    public void recibirEventoSismico(EventoSismico evento);
    
    /**
     * Genera el reporte del evento a partir de la información sobre el
     * mismo que entra por parámetro. Para esto, sigue la lógica de negocio
     * descrita en el enunciado del experimento.
     * @param evento objeto que contiene los parámetros del evento sísmico.
     * @return 
     */
    public Reporte generarReporteDeEvento(EventoSismico evento, Señal señalRecibida, long id);
    
    /**
     * Retorna la información que se tiene guardada sobre eventos sismicos
     * anteriores.
     */
    public ArrayList<EventoSismico> darEventosHistoricos();
    
    /**
     * Retorna la información que se tiene guardada sobre reportes
     * anteriores.
     */
    public ArrayList<Reporte> darReportesHistoricos();
    
   

}
