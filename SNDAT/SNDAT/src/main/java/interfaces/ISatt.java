/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import dto.DatosSismo;
import java.util.*;
import dto.Sensor;
import javax.ejb.Remote;

/**
 *
 * @author ca.carrillo2369
 */
@Remote
public interface ISatt {
    
    public List darReportes();
    
    public boolean verificarTsunami(DatosSismo dS);
    
    public Sensor buscarSensorMasCercano(double longitud, char lonD, double latitud, char latD);
    
    public ArrayList<String> determinarZonasAfectadas(Sensor sensorMasCercano);
    
    public ArrayList<Integer> calcularTiemposDeLlegada(ArrayList<String> zonasAfectadas, Sensor sensorMasCercano);
    
    public String generarReporte( ArrayList<String> zonasAfectadas, ArrayList<Integer> tiemposDeLlegada, double longitud, char lonD, double latitud, char lanD );    
}
