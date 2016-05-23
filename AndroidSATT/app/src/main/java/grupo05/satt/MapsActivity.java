package grupo05.satt;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bundle bundle = getIntent().getBundleExtra("bundle");
        final String zona = bundle.getString("zona");
        final String tiempoLlegada = bundle.getString("tiempoLlegada");
        final String altura = bundle.getString("altura");

        ArrayList<LatLng> puntos = new ArrayList<LatLng>();

        LatLng punto;

        if( zona.equals(" Atlantico") ){

            punto = new LatLng(10.814, -74.588);

            LatLng p1 = new LatLng(8.623, -76.886);
            LatLng p2 = new LatLng(12.398, -71.590);
            LatLng p3 = new LatLng(13.005, -71.984);
            LatLng p4 = new LatLng(9.212, -77.286);

            puntos.add(p1);
            puntos.add(p2);
            puntos.add(p3);
            puntos.add(p4);
        }else{

            punto = new LatLng(4.3215, -78.0525);

            LatLng p1 = new LatLng(1.436, -76.943);
            LatLng p2 = new LatLng(7.207, -76.943);
            LatLng p3 = new LatLng(7.207, -79.162);
            LatLng p4 = new LatLng(1.436, -79.162);

            puntos.add(p1);
            puntos.add(p2);
            puntos.add(p3);
            puntos.add(p4);
        }

        MarkerOptions mOptions = new MarkerOptions();
        mOptions.position(punto);
        mOptions.title("Zona afectada");

        mOptions.snippet("Tiempo de llegada: " + tiempoLlegada +"\n Altura de la Ola: " + altura);
        mMap.addMarker(mOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mOptions.getPosition()));
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                View v = getLayoutInflater().inflate(R.layout.marker_layout, null);

                TextView info= (TextView) v.findViewById(R.id.info);

                info.setText("Tiempo de llegada: " + tiempoLlegada +"\n Altura de la Ola: " + altura);

                return v;
            }
        });
        mMap.animateCamera(CameraUpdateFactory.zoomTo(6));

        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.addAll(puntos);
        polygonOptions.strokeColor(Color.BLACK);
        polygonOptions.strokeWidth(5);
        polygonOptions.fillColor( Color.argb(100, 166, 166, 166) );

        mMap.addPolygon(polygonOptions);
    }
}
