/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 * Clase que representa un evento sísmico que fue reconocido por la RSNC
 * y que contiene la información básica que fue emitida sobre este.
 * @author ac.zuleta10
 */
@NoSql(dataFormat=DataFormatType.MAPPED)
@Entity
@XmlRootElement
public class EventoSismico implements Serializable 
{
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Referencia que identifica de manera única el evento sísmico (para que persistan registros históricos sobre este)
     */
     @Id
//    @GeneratedValue
    @Field(name="_id")
    private long id;

    /**
     * Atributo que modela la latitud del evento sísmico (para conocer su ubicación)
     */
    private double latitud;

    /**
     * Atributo que modela la longitud del evento sísmico (para conocer su ubicación)
     */
    private double longitud;

    /**
     * Atributo que modela la distancia a la costa del evento sísmico.
     */
    private double distancia;

    private Señal señalCercana;
    
    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     * Este constructor está vacío para facilitar la transferencia del objeto
     * y poder modificarlo utilizando los métodos set
     * durante el tiempo de ejecución
     */
    public EventoSismico() 
    {
         //No hace nada porque  estamos ocupados haciendo dalgo
    }

    /**
     * Constructor de la clase. Inicializa los atributos con los valores que ingresan por parametro.
     * @param id Identificador único del evento sísmico
     * @param latitud Latitud del evento sísmico
     * @param longitud Longitud del evento sísmico
     * @param distancia Distancia a la costa del evento sísmico
     */
    public EventoSismico(long id, double latitud, double longitud, double distancia)
    {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.distancia = distancia;
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    
    /**
     * Devuelve el identificador del evento sísmico
     * @return el id del evento sísmico
     */
    public long getId()
    {
        return id;
    }

    /**
     * Modifica el identificador del evento sísmico
     * @param id Nuevo identificador del evento sísmico
     */
    public void setId(long id) 
    {
        this.id = id;
    }

    /**
     * Devuelve la longitud del evento sísmico
     * @return longitud Longitud del evento sísmico
     */
    public double getLongitud()
    {
        return longitud;
    }

    /**
     * Modifica la longitud del evento sísmico
     * @param longitud La nueva longitud del evento sísmico
     */
    public void setLongitud(double longitud)
    {
        this.longitud = longitud;
    }
    
    /**
     * Devuelve la latitud del evento sísmico
     * @return latitud La latitud del evento sísmico
     */
    public double getLatitud()
    {
        return latitud;
    }

    /**
     * Modifica la latitud del evento sísmico
     * @param latitud La nueva latitud del evento sísmico
     */
    public void setLatitud(double latitud)
    {
        this.latitud = latitud;
    }
    
    /**
     * Devuelve la distancia a la costa del evento sísmico
     * @return distancia La distancia a la costa del evento sísmico
     */
    public double getDistancia()
    {
        return distancia;
    }

    /**
     * Modifica la distancia a la costa del evento sísmico
     * @param distancia La nueva distancia a la costa del evento sísmico
     */
    public void setDistancia(double distancia)
    {
        this.distancia = distancia;
    }

    public Señal getSeñalCercana() {
        return señalCercana;
    }

    public void setSeñalCercana(Señal señalCercana) {
        this.señalCercana = señalCercana;
    }
    
    
}
