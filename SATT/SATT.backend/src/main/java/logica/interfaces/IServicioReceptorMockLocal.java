
package logica.interfaces;

import dto.EventoSismico;
import java.util.ArrayList;
import javax.ejb.Local;
import dto.Señal;

/**
 * Es el Mock para ser accedido localmente que ayuda con las pruebas del servicio
 * que ofrece:
 *      Un POST que recibe las señales de los sensores.
 *      Un GET que despliega las señales de los sensores guardadas hasta el momento.
 */
@Local
public interface IServicioReceptorMockLocal
{

    /**
     * Recibe una señal de un sensor y la guarda en la lista de las señales
     * recibidas para procesarla en caso de ser necesario.
     * @param señalesRecibidas lista de señales enviadas por un sensor.
     */
    public void recibirSeñal(Señal señalRecibida);
    
    /**
     * Retorna todas las señales que se tengan almacenadas. Se utiliza principalmente en el 
     * servicio que despliega la información histórica.
     */
    public ArrayList<Señal> darSeñales();


    public Señal buscarUltimoRegistroSensorCercano(EventoSismico evento); 
}
