/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import excepciones.ZonaException;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 * Clase que representa un reporte (O boletín de emergencia)
 * generado por el SATT
 * @author ac.zuleta10
 */
@NoSql(dataFormat=DataFormatType.MAPPED)
@Entity
@XmlRootElement
public class Reporte implements Serializable {      
    
     private static final long serialVersionUID = 1L;
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
     /**
      * Atributo que modela el identificador único del reporte generado.
      */
    @Id
//    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Field(name="_id")
    private long id;
    
    /**
     * Atributo que representa el perfil de alerta asociado al reporte.
     */
    private String perfilAlerta;
    
    /**
     * Atributo que modela la zona en la cual se encuentra el reporte (solo puede ser Atlantico ó Pacífico).
     */
    private String zona;
     
    /**
     * Atributo que modela las zonas geograficas específicas que fueron afectadas por el tsunami.
     */
    private ArrayList<String> zonas;
    
    /**
     * Atributo que representa el tiempo de llegada estimado del evento sísimico asociado al reporte.
     */
    private double tiempoLlegada;
    
    /**
     * Atributo que representa la altura de la ola asociada al reporte.
     */
    private double altura;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     */
    public Reporte()
    {
        /*
        * Este constructor está vacío para facilitar la transferencia del objeto
     * y poder modificarlo utilizando los métodos set
     * durante el tiempo de ejecución
        */
    }
    
     /**
     * Constructor de la clase. Inicializa los atributos con los valores que ingresan por parametro.
     * @param id Identificador único del reporte
     * @param perfilAlerta perfil de alerta asociado al reporte
     * @param zona zona en la cual se realiza el reporte.
     * @param tiempoLlegada tiempo estimado de llegada del tsunami
     * @param altura altura de la ola asociada al tsunami
     */
    public Reporte(long id, String perfilAlerta, String zona, double tiempoLlegada, double altura)
    {
        this.id = id;
        this.perfilAlerta = perfilAlerta;
        this.zona = zona;
        this.tiempoLlegada = tiempoLlegada;
        this.altura = altura;
    }
    
    /**
     * Constructor de la clase. Inicializa los atributos con los valores que ingresan por parametro.
     * @param id Identificador único del reporte
     * @param perfilAlerta perfil de alerta asociado al reporte
     * @param zona zona en la cual se realiza el reporte.
     * @param tiempoLlegada tiempo estimado de llegada del tsunami
     * @param altura altura de la ola asociada al tsunami
     * @param zonas Zonas geográficas afectadas por el tsunami
     */
 
    public Reporte(long id, String perfilAlerta, String zona, double tiempoLlegada, double altura
    ,List zonas)
    {
        this.id = id;
        this.perfilAlerta = perfilAlerta;
        this.zona = zona;
        this.tiempoLlegada = tiempoLlegada;
        this.altura = altura;
        this.zonas = new ArrayList<>();
        
        for(int i = 0; i < zonas.size(); i++)
        {
            this.zonas.add((String) zonas.get(i));
        }
    }
    
    /**
     * Constructor de la clase. Inicializa los atributos con los valores que ingresan por parametro.
     * @param perfilAlerta perfil de alerta asociado al reporte
     * @param zona zona en la cual se realiza el reporte.
     * @param tiempoLlegada tiempo estimado de llegada del tsunami
     * @param altura altura de la ola asociada al tsunami
     * @param zonas Zonas geográficas afectadas por el tsunami
     */
    public Reporte(String perfilAlerta, String zona, double tiempoLlegada, double altura
    , List zonas)
    {
        this.perfilAlerta = perfilAlerta;
        this.zona = zona;
        this.tiempoLlegada = tiempoLlegada;
        this.altura = altura;
        this.zonas = new ArrayList<>();
        
        for(int i = 0; i < zonas.size(); i++)
        {
            this.zonas.add((String) zonas.get(i));
        }
    }
    
    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    
    /**
     * método que retorna el identificador único asociado al reporte.
     * @return el id del reporte
     */
    public long getId() 
    {
        return id;
    }

    /**
     * Método que modifica el identificador único del reporte.
     * @param id  el nuevo id del reporte.
     */
    public void setId(long id) 
    {
        this.id = id;
    }

    /**
     * Método que retorna el perfil de alerta del reporte.
     * @return perfilAlerta del reporte.
     */
    public String getPerfilAlerta() 
    {
        return perfilAlerta;
    }

    /**
     * Método que modifica el perfil de alerta asociado al reporte.
     * @param perfilAlerta Nuevo perfil de alerta asociado al reporte.
     */
    public void setPerfilAlerta(String perfilAlerta) 
    {
        this.perfilAlerta = perfilAlerta;
    }

    /**
     * Método que retorna la zona costera asociada al reporte.
     * @return zona La zona asociada al reporte.
     */
    public String getZona() 
    {
        return zona;
    }

    /**
     * Método que modifica la zona costera asociada al reporte.
     * @param zona nueva zona costera asociada al reporte.
     * @throws  Exception si se le intenta colocar una zona no válida.
     */
    public void setZona(String zona) throws ZonaException
    {   
        if("Atlantico".equals(zona) || "Pacifico".equals(zona))
        {
          this.zona = zona;
        } 
         
        else
        {
            throw new ZonaException ("La zona costera asociada solo puede ser la costa atlantica o pacifica");
        }
       
    }

    /**
     * Método que retorna el tiempo de llegada estimado del tsunami asociado al reporte.
     * @return tiempoLlegada El tiempo de llegada estimado del tsunami.
     */
    public double getTiempoLlegada() 
    {
        return tiempoLlegada;
    }

    /**
     * Método que modifica el tiempo de llegada estimado del tsunami.
     * @param tiempoLlegada El nuevo tiempo de llegada estimado
     */
    public void setTiempoLlegada(double tiempoLlegada) 
    {
        this.tiempoLlegada = tiempoLlegada;
    }

    /**
     * Método que retorna la altura de la ola del tsunami.
     * @return altura La altura de la ola del tsunami asociado al reporte.
     */
    public double getAltura() 
    {
        return altura;
    }

    /**
     * Método que modifica la altura de la ola del tsunami asociado al reporte.
     * @param altura La nueva altura de la ola asociada al tsunami.
     */
    public void setAltura(double altura) 
    {
        this.altura = altura;
    }
    
    /**
     * Método que retorna las zonas afectadas por el tsunami.
     * @return zonas Las zonas afectadas
     */
     public List<String> getZonas()
     {
         return zonas;
     }
     
     /**
      * Método que modifica las zonas afectadas por el tsunami
      * por unas que entran por parámetro.
      * @param zonas ArrayList que contiene las zonas afectadas
      */
     public void setZonas(List<String> zonas)
     {
         this.zonas.clear();
         
         for(int i = 0; i<zonas.size(); i++)
         {
             this.zonas.add(zonas.get(i));
         }
     }
}
