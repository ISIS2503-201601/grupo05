/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author ca.carrillo2369
 */
public class DatosSismo {
    
    private double longitud;
    private String lonD;
    private double latitud;
    private String latD;
    private double distancia;
    
    public DatosSismo(){
    }

    public DatosSismo(double longitud, String lonD, double latitud, String latD, double distancia) {
        this.longitud = longitud;
        this.lonD = lonD;
        this.latitud = latitud;
        this.latD = latD;
        this.distancia = distancia;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getLonD() {
        return lonD;
    }

    public double getLatitud() {
        return latitud;
    }

    public String getLatD() {
        return latD;
    }

    public double getDistancia() {
        return distancia;
    }   
}
