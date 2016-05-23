package grupo05.satt;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaSensores extends Fragment {

    ArrayAdapter<String> mForecastAdapter;

    public ListaSensores() {
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
        View rootView = inflater.inflate(R.layout.fragment_lista_sensores, container, false);

        String jsonString = getArguments().getString("Json");

        try {
            JSONArray lista = new JSONArray(jsonString);

            String[] data = new String[lista.length()];

            for (int i = 0; i < lista.length(); i++) {
                JSONObject explrObject = lista.getJSONObject(i);
                int id = explrObject.getInt("id");
                double latitud = explrObject.getInt("latitud");
                double longitud = explrObject.getInt("longitud");
                double alturaOlas = explrObject.getInt("alturaOlas");
                double velocidadOlas = explrObject.getInt("velocidadOlas");

                data[i] = "Id: " + id + "\n Latitud: " + latitud + "\n Longitud: " + longitud + "\n Altura de Olas: " + alturaOlas + "\n Velocidad de Olas: " + velocidadOlas;
            }

            List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));

            mForecastAdapter =
                    new ArrayAdapter<String>(
                            getActivity(), // The current context (this activity)
                            R.layout.list_item_forecast, // The name of the layout ID.
                            R.id.list_item_forecast_textview, // The ID of the textview to populate.
                            weekForecast);

            // Get a reference to the ListView, and attach this adapter to it.
            ListView listView = (ListView) rootView.findViewById(R.id.listview_sensores);
            listView.setAdapter(mForecastAdapter);

        }catch (Exception e){
            e.printStackTrace();
        }
        return rootView;
    }
}
