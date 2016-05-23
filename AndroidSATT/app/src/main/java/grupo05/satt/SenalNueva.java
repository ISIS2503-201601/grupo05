package grupo05.satt;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SenalNueva extends Fragment implements View.OnClickListener {

    View viewPrincipal = null;
    public SenalNueva(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_senal_nueva, container, false);
        viewPrincipal = rootView;
        Button btn = (Button) rootView.findViewById(R.id.btnEnviarSeñal);
        btn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();

        if( itemId == R.id.btnEnviarSeñal ){

            try {

                String[] params = new String[5];
                params[0] = ((EditText)viewPrincipal.findViewById(R.id.inpIdSeñalNueva) ).getText().toString(); //id
                params[1] = ((EditText)viewPrincipal.findViewById(R.id.inpLatitudSeñalNueva) ).getText().toString(); //Latitud
                params[2] = ((EditText)viewPrincipal.findViewById(R.id.inpLongitudSeñalNueva) ).getText().toString(); //Longitud
                params[3] = ((EditText)viewPrincipal.findViewById(R.id.inpAlturaOlaSeñalNueva) ).getText().toString(); //Altura de la Ola
                params[4] = ((EditText)viewPrincipal.findViewById(R.id.inpVelocidadOlaSeñalNueva) ).getText().toString(); //Velocidad de la Ola

                UrlAsyncTask asynProc = new UrlAsyncTask(UrlAsyncTask.GENERAR_SEÑAL, "http://192.168.0.18:8080/SATT.servicios/webresources/receptor/enviar", params);
                asynProc.execute();

                Toast.makeText(getActivity(), "Señal enviada", Toast.LENGTH_SHORT).show();

                //getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

                Fragment mainMenuFragment = new MainMenuFragment();

                mainMenuFragment.setArguments(getArguments());
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(this.getId(), mainMenuFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Complete todos los espacios", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
