/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dto.DatosSismo;
import dto.Sensor;
import persistencia.PersistenciaSatt;
import interfaces.ISatt;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author ca.carrillo2369
 */
@Stateless
public class Satt implements ISatt {
    
    private PersistenciaSatt persistencia;
    
    public Satt(){
        persistencia = new PersistenciaSatt();
    }

    public List darReportes(){
        return persistencia.findAll();
    }
    
    @Override
    public boolean verificarTsunami(DatosSismo dS) {
        boolean tsunami = false;
        
            Sensor masCercano = buscarSensorMasCercano(dS.getLongitud(), dS.getLonD().charAt(0), dS.getLatitud(), dS.getLatD().charAt(0));
            ArrayList<String> zonasAfectadas = determinarZonasAfectadas(masCercano);
            if( !zonasAfectadas.isEmpty() ){
                ArrayList<Integer> tiemposDeLlegada = calcularTiemposDeLlegada( zonasAfectadas, masCercano );
                generarReporte(zonasAfectadas, tiemposDeLlegada, dS.getLongitud(), dS.getLonD().charAt(0), dS.getLatitud(), dS.getLatD().charAt(0));
                tsunami = true;
            }           
            
        return tsunami;
    }

    @Override
    public Sensor buscarSensorMasCercano(double longitud, char lonD, double latitud, char latD) {
        double longSen = longitud + (Math.random() * 5);
        char lonDS = 'O';
        double latSen = latitud + (Math.random() * 5);
        char latDS = 'N';
        int altura = (int)(Math.random() * 2000);
        double velocidad = ThreadLocalRandom.current().nextInt(180, 221);
        
        Sensor masCercano =  new Sensor(longSen, lonDS, latSen, latDS, altura, velocidad);
        
        return masCercano;
    }
    
    public ArrayList<String> determinarZonasAfectadas(Sensor sensorMasCercano){
        ArrayList<String> zonas =  new ArrayList<String>();
        
        if( sensorMasCercano.getLongitud() > 70 && sensorMasCercano.getLatitud() < 8 ){
            //Pacifico
            if(Math.random() > .4){zonas.add("Chocó");}
            if(Math.random() > .4){zonas.add("Valle del Cauca");}
            if(Math.random() > .4){zonas.add("Cauca");}
            if(Math.random() > .4){zonas.add("Nariño");}
        }else{
            //Atlantico
            if(Math.random() > .4){zonas.add("Guajira");}
            if(Math.random() > .4){zonas.add("Magdalena");}
            if(Math.random() > .4){zonas.add("Sucre");}
            if(Math.random() > .4){zonas.add("Córdoba");}
            if(Math.random() > .4){zonas.add("Antioquia");}
            if(Math.random() > .4){zonas.add("Atlantico");}
            if(Math.random() > .4){zonas.add("Bolivar");}
        }
        
        return zonas;
    }
    
    @Override
    public ArrayList<Integer> calcularTiemposDeLlegada( ArrayList<String> zonasAfectadas, Sensor sensorMasCercano ){
        ArrayList<Integer> tiempos =  new ArrayList<Integer>();
        
        for (int i = 0; i < zonasAfectadas.size(); i++ ) {
            String zonaAct = zonasAfectadas.get(i);
            tiempos.add(calcularTiempoParaZona(zonaAct));
        }
        
        return tiempos;
    }
    
    public int calcularTiempoParaZona( String zona){
        return (int)(Math.random()*60);
    }

    @Override
    public String generarReporte( ArrayList<String> zonasAfectadas, ArrayList<Integer> tiemposDeLlegada, double longitud, char lonD, double latitud, char latD ) {
        String reporte = "Temblor en " + latitud + " grados " + latD + ", " + longitud + " grados " + lonD + ".";
        try {
            persistencia.create(reporte);
        } catch (Exception ex) {
            Logger.getLogger(Satt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reporte;
    }    
}