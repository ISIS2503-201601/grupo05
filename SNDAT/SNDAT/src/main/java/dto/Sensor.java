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
public class Sensor {
    
    private double longitud;
    private char lonD;
    private double latitud;
    private char latD;
    private int altura;
    private double velocidadDeLaUltimaOla;

    public Sensor(double longitud, char lonD, double latitud, char latD, int altura, double velocidadDeLaUltimaOla) {
        this.longitud = longitud;
        this.lonD = lonD;
        this.latitud = latitud;
        this.latD = latD;
        this.altura = altura;
        this.velocidadDeLaUltimaOla = velocidadDeLaUltimaOla;
    }

    public double getLongitud() {
        return longitud;
    }
    
    public char getLonDirection(){
        return lonD;
    }

    public double getLatitud() {
        return latitud;
    }
    
    public char getLatDirection(){
        return latD;
    }

    public int getAltura() {
        return altura;
    }

    public double getVelocidadDeLaUltimaOla() {
        return velocidadDeLaUltimaOla;
    }    
}
