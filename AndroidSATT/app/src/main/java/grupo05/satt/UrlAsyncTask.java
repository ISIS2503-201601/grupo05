package grupo05.satt;

import android.net.Uri;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by CamiloC on 19/05/2016.
 */
public class UrlAsyncTask extends AsyncTask {

    public final static String GET = "GET";
    public final static String GENERAR_REPORTE = "GENERAR_REPORTE";
    public final static String GENERAR_SEÑAL = "GENERAR_SEÑAL";

    private String metodo;
    private String strUrl;
    private String[] parametros;

    private String respuesta;

    // nRul Ej : https://172.24.100.47:8080/webresources/reportes/mostrar
    // Tipo Ej : GET, POST...
    public UrlAsyncTask ( String nMetodo, String nUrl, String[] nParametros ){
        metodo = nMetodo;
        strUrl = nUrl;
        parametros = nParametros;
        respuesta = "";
    }

    //public String[] getJsonData(String forecastJsonStr){

    //}
    public String darRespuesta(){
        return respuesta;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        try{
            StringBuilder resultado = new StringBuilder();

            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if( metodo.equals( GENERAR_REPORTE ) || metodo.equals( GENERAR_SEÑAL ) ){

                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setUseCaches(false);

                urlConnection.connect();

                JSONArray list = new JSONArray();
                JSONObject obj = new JSONObject();
                obj.put("id", Integer.parseInt(parametros[0].toString()) );
                obj.put("latitud", Double.parseDouble(parametros[1].toString()) );
                obj.put("longitud", Double.parseDouble(parametros[2].toString()) );
                if( metodo.equals( GENERAR_REPORTE ) ){
                    obj.put("distancia", Double.parseDouble(parametros[3].toString()) );
                }else{
                    obj.put("alturaOlas", Double.parseDouble(parametros[3].toString()) );
                    obj.put("velocidadOlas", Double.parseDouble(parametros[4].toString()) );
                }

                list.put(obj);

                OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());
                osw.write(list.toString());
                osw.flush();

                int HttpResult =urlConnection.getResponseCode();
                if(HttpResult ==HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(), "utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        resultado.append(line + "\n");
                    }
                    br.close();
                }

                    Log.i("System.out", "Termino POST");
            }else {
                urlConnection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    resultado.append(line);
                }

                Log.i("System.out", "Termino GET");
            }
            respuesta  = resultado.toString();
            urlConnection.disconnect();
        }catch (Exception e0){
            e0.printStackTrace();
            Log.i("System.out", "Excepción!");
        }
        return null;
    }
}
