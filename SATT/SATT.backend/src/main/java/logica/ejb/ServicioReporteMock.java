
package logica.ejb;

import dto.Reporte;
import dto.EventoSismico;
import dto.Modelo;
import dto.Señal;
import logica.interfaces.IServicioReporteMockRemote;
import logica.interfaces.IServicioReporteMockLocal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Implementacion de los servicios del carrito de compras en el sistema.
 * @author Juan Sebastián Urrego editado por Edgar Sandoval
 */

@Stateless
public class ServicioReporteMock implements IServicioReporteMockLocal
{
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Lista que contiene la información de eventos sísmicos anteriores
     */
    private ArrayList<EventoSismico> listaEventosSismicos;
    
    /**
     * Lista que contiene los reportes generados anteriormente acerca de eventos
     * sísmiscos recibidos.
     */
    private ArrayList<Reporte> listaReportes;
    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    
    /**
     * Constructor sin argumentos de la clase
     */
    public ServicioReporteMock()
    {
        listaEventosSismicos = new ArrayList<>();
        listaReportes = new ArrayList<>();
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    @Override
    public void recibirEventoSismico(EventoSismico evento)
    {
       listaEventosSismicos.add(evento);
    }
    
    @Override
    public Reporte generarReporteDeEvento(EventoSismico evento, Señal señalRecibida, long id) 
    {   
        
        //el evento tiene también lat y long, por qué usas la señal-?
        // Poruqe me interesa el sensor que envío la señal, no?
        //pero "lo" que envía los parámetros de un evento sísmic o no es un sensor, son los manes
        //de otra organización ahí
        //Se instancian los modelos premodelados
        Modelo modeloA1 = new Modelo(10000, 100000, 0, 5, "Atlantico", "Informativo" );
        Modelo modeloA2 = new Modelo(10000, 100000, 5, 10, "Atlantico", "Precaucion" );
        Modelo modeloA3 = new Modelo(10000, 100000, 10, 20, "Atlantico", "Alerta" );
        Modelo modeloA4 = new Modelo(10000, 100000, 20, 30, "Atlantico", "Alarma" );
        Modelo modeloP1 = new Modelo(10000, 100000, 0, 5, "Pacifico", "Informativo" );
        Modelo modeloP2 = new Modelo(10000, 100000, 5, 10, "Pacifico", "Precaucion" );
        Modelo modeloP3 = new Modelo(10000, 100000, 10, 20, "Pacifico", "Alerta" );
        Modelo modeloP4 = new Modelo(10000, 100000, 20, 30, "Pacifico", "Alarma" );
        
        ArrayList<Modelo> premodelados = new ArrayList<>();
        premodelados.add(modeloA1);
        premodelados.add(modeloA2);
        premodelados.add(modeloA3);
        premodelados.add(modeloA4);
        premodelados.add(modeloP1);
        premodelados.add(modeloP2);
        premodelados.add(modeloP3);
        premodelados.add(modeloP4);
        
        String[] zonasAtlantico = {"Guajira", "Magdalena", "Sucre", "Córdoba", "Antioquia"};
        String[]zonasPacifico = {"Chocó", "Valle del Cauca", "Cauca", "Nariño"};
        String[] perfiles = {"informativo", "precaución", "alerta", "alarma"};
      
        ArrayList<String> zonas = new ArrayList<>();
        String zona;
        String perfil = "informativo";
        
         
        //Calculo de zona costera mas cercana
        if(señalRecibida.getLatitud()< 12 && señalRecibida.getLatitud()> 11 
        && señalRecibida.getLongitud() > -75 && señalRecibida.getLongitud() < -74)
        {
           zona = "Atlantico";
           int zonaAfectada = (int) Math.random() * 5;
           zonas.add(zonasAtlantico[zonaAfectada]); 
        }
            
        else if(señalRecibida.getLatitud()< 7 && señalRecibida.getLatitud()> 5 
        && señalRecibida.getLongitud()< -77 && señalRecibida.getLongitud()> -78)
        {
           zona = "Pacifico";
           int zonaAfectada = (int) Math.random() * 4;
           zonas.add(zonasPacifico[zonaAfectada]);
        }
        
        else
        {
            zona = "";
        }
        
        //Se calcula el tiempo de llegada de la ola
        double tiempo = evento.getDistancia()/señalRecibida.getVelocidadOlas();
        
        /**
         * Se encuentra el perfil de alerta segun escenarios 
         * premodelados
         */
        for(int i = 0; i < premodelados.size(); i++)
        {
          Modelo modeloActual = premodelados.get(i);
          if(zona.equals(modeloActual.getZona()) && señalRecibida.getAlturaOlas() >= 
          modeloActual.getAlturaMinima() && señalRecibida.getAlturaOlas() <= 
          modeloActual.getAlturaMaxima() && tiempo >= modeloActual.getTiempoMinimo() 
          && tiempo <= modeloActual.getTiempoMaximo())
          {
                      perfil = modeloActual.getPerfil();
                 
          }
        }
        
        Reporte retorno = new Reporte(id, perfil, zona, tiempo, señalRecibida.getAlturaOlas(), zonas);
        listaReportes.add(retorno);
        return retorno;
    }

    @Override
    public List<EventoSismico> darEventosHistoricos()
    {
       return listaEventosSismicos;
    }
    
    @Override
    public List<Reporte> darReportesHistoricos()
    {
       return listaReportes;
    }
    
    
    
}
