/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *Clase que representa un modelo premodelado del sistema SATT.
 * @author ac.zuleta10
 */
public class Modelo 
{
  //-----------------------------------------------------------
  // Atributos
  //-----------------------------------------------------------
    
  /**
   * Atributo que modela el tiempo de llegada mínimo del modelo premodelado
   */  
  private double tiempoMinimo;
  
  /**
   * Atributo que modela el tiempo de llegada maximo del tsunami del modelo
   * premodelado.
   */
  private double tiempoMaximo;
  
  /**
   * Atributo que modela la altura mínima de la ola del model premodelado.
   */
  private double alturaMinima;
  
  /**
   * Atributo que modela la altura máxima de la ola del modelo premodelado.
   */
  private double alturaMaxima;
  
  /**
   * Atributo que modela la zona asociada al modelo premodelado.
   */
  private String zona;
  
  /**
   * Atributo que modela el perfil de laerta asociado al modelo premodelado.
   */
  private String perfil;

  
  /**
   * Constructor sin parámetros de la clase
   */
  public Modelo() 
  {
      /*
      * Este constructor está vacío para facilitar la transferencia del objeto
     * y poder modificarlo utilizando los métodos set
     * durante el tiempo de ejecución
     */
      
  }

  //-----------------------------------------------------------
  // Constructores
  //-----------------------------------------------------------
  
    public Modelo(double tiempoMinimo, double tiempoMaximo, double alturaMinima, 
            double alturaMaxima, String zona, String perfil) 
    {
        this.tiempoMinimo = tiempoMinimo;
        this.tiempoMaximo = tiempoMaximo;
        this.alturaMinima = alturaMinima;
        this.alturaMaxima = alturaMaxima;
        this.zona = zona;
        this.perfil = perfil;
    }

    //-----------------------------------------------------------
    // Getters y Setters
    //-----------------------------------------------------------
    
    public double getTiempoMinimo() {
        return tiempoMinimo;
    }

    public void setTiempoMinimo(double tiempoMinimo) {
        this.tiempoMinimo = tiempoMinimo;
    }

    public double getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(double tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public double getAlturaMinima() {
        return alturaMinima;
    }

    public void setAlturaMinima(double alturaMinima) {
        this.alturaMinima = alturaMinima;
    }

    public double getAlturaMaxima() {
        return alturaMaxima;
    }

    public void setAlturaMaxima(double alturaMaxima) {
        this.alturaMaxima = alturaMaxima;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    
  
  
  
  
}
