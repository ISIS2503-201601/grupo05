
package logica.interfaces;

import java.util.List;
import javax.ejb.Remote;
import dto.Señal;
import java.util.ArrayList;

/**
 * Es el Mock para ser accedido de forma remota que ayuda con las pruebas unitarias del servicio
 * que recibe las señales de los sensores.
 */
@Remote
public interface IServicioReceptorMockRemote
{

      /**
     * Recibe una señal de un sensor y la guarda en la lista de las señales
     * recibidas para procesarla en caso de ser necesario.
     * @param señalesRecibidas lista de señales enviadas por un sensor.
     */
    public boolean recibirSeñal(Señal señalRecibida);
    
    /**
     * Retorna todas las señales que se tengan almacenadas. Se utiliza principalmente en el 
     * servicio que despliega la información histórica.
     */
    public ArrayList<Señal> darSeñales();

}
