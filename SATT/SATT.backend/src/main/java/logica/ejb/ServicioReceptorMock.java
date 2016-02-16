package  logica.ejb;

import dto.EventoSismico;
import dto.Señal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import logica.interfaces.IServicioReceptorMockRemote;
import logica.interfaces.IServicioReceptorMockLocal;


@Stateless
public class ServicioReceptorMock implements IServicioReceptorMockLocal
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    private ArrayList<Señal> listaSeñales;
    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos que inicializa la lista de señales 
     * recibidas.
     */
    public ServicioReceptorMock()
    {
       listaSeñales = new ArrayList<Señal>();
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    
    @Override
    public void recibirSeñal(Señal señalRecibida) 
    {
        listaSeñales.add(señalRecibida);
    }

    @Override
    public ArrayList<Señal> darSeñales() 
    {
       return listaSeñales;
    }

    
    /**
     * Método que retorna el sensor más cercano al lugar del evento sísmico.
     * @param latitud
     * @param longitud
     * @return 
     */
    @Override
    public Señal buscarUltimoRegistroSensorCercano(EventoSismico evento) 
    {
        double latitud = evento.getLatitud();
        double longitud = evento.getLongitud();
        double distancia = evento.getDistancia();
        
        Señal cercano = listaSeñales.get(0);
        double distanciaMin = Math.sqrt( Math.pow( (latitud - cercano.getLatitud() ), 2) + 
                Math.pow( (longitud - cercano.getLongitud()), 2 ) );
        double d = 0;
        
        for(Señal señal : listaSeñales)
        {
            d = Math.sqrt( Math.pow( (latitud - cercano.getLatitud() ), 2) + 
                Math.pow( (longitud - cercano.getLongitud()), 2 ) );
            
            if(d < distanciaMin)
            {
                distanciaMin = d;
                cercano = señal;
            }
            
        }
     
        return cercano;
    }

}