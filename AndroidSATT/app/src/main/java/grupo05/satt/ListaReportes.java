package grupo05.satt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaReportes extends Fragment implements AdapterView.OnItemClickListener {

    ArrayAdapter<String> mForecastAdapter;

    public ListaReportes() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_reportes, container, false);
        String jsonString = getArguments().getString("Json");

        try {
            JSONArray lista = new JSONArray(jsonString);

            String[] data = new String[lista.length()];

            for (int i = 0; i < lista.length(); i++) {
                String dataStr = "";

                JSONObject explrObject = lista.getJSONObject(i);
                int id = explrObject.getInt("id");
                String perfilAlerta = explrObject.getString("perfilAlerta");
                String zona = explrObject.getString("zona");
                JSONArray zonas = explrObject.getJSONArray("zonas");

                dataStr += "Id: " + id + "\n perfilAlerta: " + perfilAlerta + "\n Zona: " + zona + "\n Regiones afectadas: ";

                for (int j = 0; j< zonas.length(); j++){
                    String zonaAct = zonas.getString(j);
                    dataStr += "\n - " + zonaAct;
                }

                double tiempoLlegada = explrObject.getDouble("tiempoLlegada");
                double altura = explrObject.getDouble("altura");

                dataStr += "\n Tiempo de llegada: " + tiempoLlegada + "\n Altura: " + altura;

                data[i] = dataStr;
            }

            List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));

            mForecastAdapter =
                    new ArrayAdapter<String>(
                            getActivity(), // The current context (this activity)
                            R.layout.list_item_forecast, // The name of the layout ID.
                            R.id.list_item_forecast_textview, // The ID of the textview to populate.
                            weekForecast);

            // Get a reference to the ListView, and attach this adapter to it.
            ListView listView = (ListView) rootView.findViewById(R.id.listview_reportes);
            listView.setOnItemClickListener(this);
            listView.setAdapter(mForecastAdapter);

        }catch (Exception e){
            e.printStackTrace();
        }
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView c = ( TextView ) parent.getChildAt(position);
        String texto = c.getText().toString();
        String[] d = texto.split("\n");
        String zona = texto.split("\n")[2].split(":")[1];
        String tiempoLlegada = texto.split("\n")[d.length-2].split(":")[1];
        String altura = texto.split("\n")[d.length-1].split(":")[1];

        Intent intent = new Intent(getActivity(), MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("zona", zona);
        bundle.putString("tiempoLlegada", tiempoLlegada);
        bundle.putString("altura", altura);
        intent.putExtra("bundle", bundle);
        startActivity(intent);

//        Toast.makeText(getActivity(), texto, Toast.LENGTH_LONG).show();
    }
}
