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
       listaSeñales = new ArrayList<>();
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    
    @Override
    public boolean recibirSeñal(Señal señalRecibida) 
    {   
        boolean existe = false;
        
        for(int i = 0; i < listaSeñales.size(); i++)
        {
            if(señalRecibida.getId() == listaSeñales.get(i).getId() )
            {
                existe = true;
                double varianza = (double) Math.abs(señalRecibida.getAlturaOlas() - listaSeñales.get(i).getAlturaOlas()) ;
                if(varianza >= 1.5)
                {
                    listaSeñales.get(i).setAlturaOlas(señalRecibida.getAlturaOlas());
                    listaSeñales.get(i).setVelocidadOlas(señalRecibida.getVelocidadOlas());
                }
                
                return false;
            }
        }
        
        if(!existe)
        {
            listaSeñales.add(señalRecibida);
            
        }
        
        return true;
    }

    @Override
    public List<Señal> darSeñales() 
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
        
        Señal cercano = listaSeñales.get(0);
        double distanciaMin = Math.sqrt( Math.pow( latitud - cercano.getLatitud(), 2) + 
                Math.pow( longitud - cercano.getLongitud(), 2 ) );
        double d;
        
        for(int i = 0; i < listaSeñales.size(); i++)
        {   
            Señal señalActual = listaSeñales.get(i);
            d = Math.sqrt( Math.pow( latitud - señalActual.getLatitud() , 2) + 
                Math.pow( longitud - señalActual.getLongitud(), 2 ) );
            
            if(d < distanciaMin)
            {
                distanciaMin = d;
                cercano = señalActual;
            }
            
        }
     
        return cercano;
    }

    @Override
    public void añadirSeñal(Señal aña)
    {
       listaSeñales.add(aña);
    }
}
