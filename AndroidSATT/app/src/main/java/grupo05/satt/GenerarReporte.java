package grupo05.satt;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GenerarReporte extends Fragment implements View.OnClickListener {

    View viewPrincipal = null;
    public GenerarReporte() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_generar_reporte, container, false);
        viewPrincipal = rootView;
        Button btn = (Button) rootView.findViewById(R.id.btnEnviarReporte);
        btn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();

        if( itemId == R.id.btnEnviarReporte ){

            try {
                String[] params = new String[4];
                params[0] = ((EditText)viewPrincipal.findViewById(R.id.inpIdReporteNuevo) ).getText().toString(); //Id
                params[1] = ((EditText)viewPrincipal.findViewById(R.id.inpLatitudReporteNuevo) ).getText().toString(); //Latitud
                params[2] = ((EditText)viewPrincipal.findViewById(R.id.inpLongitudReporteNuevo) ).getText().toString(); //Longitud
                params[3] = ((EditText)viewPrincipal.findViewById(R.id.inpDistanciaReporteNuevo) ).getText().toString(); //Distancia

                UrlAsyncTask asynProc = new UrlAsyncTask(UrlAsyncTask.GENERAR_REPORTE, "http://192.168.0.18:8080/SATT.servicios/webresources/reportes/enviar", params);
                asynProc.execute();

                Toast.makeText(getActivity(), "Reporte enviado", Toast.LENGTH_SHORT).show();

                //getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

                Fragment mainMenuFragment = new MainMenuFragment();

                mainMenuFragment.setArguments(getArguments());
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(this.getId(), mainMenuFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }catch (Exception e) {

                Toast.makeText(getActivity(), "Complete todos los espacios", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
