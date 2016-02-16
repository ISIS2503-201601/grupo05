
package dto;

/**
 * DTO que representa la señal que se recibe desde un sensor.
 * @author sm.madera10
 */
public class Señal 
{
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Identificador del sensor que envió la señal
     */
    private Long id;

    /**
     * Componente de longitud de la ubicación del sensor que envió la señal
     */
    private double longitud;
    
    /**
     * Componente de latitud de la ubicación del sensor que envió la señal
     */
    private double latitud;
    
    /**
     * Altura de las olas que es enviada en la señal
     */
    private double alturaOlas;
    
    /**
     * Atributo que modela la velocidad de las olas percibidas por el sensor y enviadas
     * en la señal que el sensor manda.
     */
    private double velocidadOlas;
    
    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------

    public Señal() 
    {
        
        
    }

    
    public Señal(Long id, double longitud, double latitud, double alturaOlas, double velocidadOlas) 
    {
        this.id = id;
        this.longitud = longitud;
        this.latitud = latitud;
        this.alturaOlas = alturaOlas;
        this.velocidadOlas = velocidadOlas;
    }
    
    
    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public double getLongitud() 
    {
        return longitud;
    }

    public void setLongitud(double longitud) 
    {
        this.longitud = longitud;
    }

    public double getLatitud() 
    {
        return latitud;
    }

    public void setLatitud(double latitud) 
    {
        this.latitud = latitud;
    }

    public double getAlturaOlas() 
    {
        return alturaOlas;
    }

    public void setAlturaOlas(double alturaOlas) 
    {
        this.alturaOlas = alturaOlas;
    }

    public double getVelocidadOlas() 
    {
        return velocidadOlas;
    }

    public void setVelocidadOlas(double velocidadOlas) 
    {
        this.velocidadOlas = velocidadOlas;
    }
    
    
}
