package grupo05.satt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CamiloC on 09/05/2016
 */

/**
 * Ip de Zuleta
 */
public class MainMenuFragment extends Fragment implements AdapterView.OnItemClickListener {

    ArrayAdapter<String> mForecastAdapter;

    String usuario;

    public MainMenuFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/Up button, so long
        //as you specify a parent activity in AndroidManifest.xml.
        int itemId = item.getItemId();

        if( itemId == R.id.cerrar_sesion ){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // ListView Clicked item index
        String resp = "";
        int itemPosition = position;
        if( itemPosition == 0 ){
            //Dar historico de reportes
            UrlAsyncTask asynProc = new UrlAsyncTask(UrlAsyncTask.GET, "http://192.168.0.18:8080/SATT.servicios/webresources/reportes/mostrar", null);
            asynProc.execute();
            while ( ( resp = asynProc.darRespuesta() ).equals("") ){

            }
            Log.i("System.out", "Respuesta desde MainMenu0: " + resp);

            Bundle bundle = new Bundle();
            bundle.putString("Json", resp);

            Fragment listaReportesFragment = new ListaReportes();
            listaReportesFragment.setArguments(bundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(this.getId(), listaReportesFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        }else if( itemPosition == 1 ){
            //Mostrar todas las señales
            UrlAsyncTask asynProc = new UrlAsyncTask(UrlAsyncTask.GET, "http://192.168.0.18:8080/SATT.servicios/webresources/receptor/mostrar", null);
            asynProc.execute();
            while ( ( resp = asynProc.darRespuesta() ).equals("") ){

            }
            Log.i("System.out", "Respuesta desde MainMenu1: " + resp);

            Bundle bundle = new Bundle();
            bundle.putString("Json", resp);

            Fragment listaSensoresFragment = new ListaSensores();
            listaSensoresFragment.setArguments(bundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(this.getId(), listaSensoresFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();

        }else if( itemPosition == 2 ){
            //Recibir señal
            Bundle bundle = new Bundle();
            bundle.putString("Usuario", usuario);

            Fragment senalFragment = new SenalNueva();
            senalFragment.setArguments(bundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(this.getId(), senalFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();

        }else if( itemPosition == 3 ){
            //Registrar evento sismico
            Bundle bundle = new Bundle();
            bundle.putString("Usuario", usuario);

            Fragment reporteFragment = new GenerarReporte();
            reporteFragment.setArguments(bundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(this.getId(), reporteFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
//        Toast.makeText(getActivity(), resp, Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);

        usuario = getArguments().getString("Usuario");
        TextView textView = (TextView) rootView.findViewById(R.id.lblUsuarioActual);
        textView.setText("Bienvenido " + usuario);

        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        String[] data = {
                "Dar historico de reportes",
                "Mostrar todas las señales",
                "Recibir señal",
                "Registrar evento sismico"
        };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));

        // Now that we have some dummy forecast data, create an ArrayAdapter.
        // The ArrayAdapter will take data from a source (like our dummy forecast) and
        // use it to populate the ListView it's attached to.
        mForecastAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.list_item_forecast, // The name of the layout ID.
                        R.id.list_item_forecast_textview, // The ID of the textview to populate.
                        weekForecast);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setOnItemClickListener(this);
        listView.setAdapter(mForecastAdapter);

        return rootView;
    }
}
