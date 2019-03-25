package org.selecciondecampeones.baikap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LugaresActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);
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

        // Add a marker in Sydney and move the camera
        LatLng pachacamacMexicano = new LatLng(-12.2030146, -76.8490809);
        LatLng cieneguilla = new LatLng( -12.1185067, -76.8161714);
        LatLng totoritas = new LatLng(-12.6787868, -76.6530128);
        LatLng santaEulalia = new LatLng(-11.9028264, -76.6674042);
        LatLng morroSolar = new LatLng(-12.1886629, -77.0362636);

        mMap.addMarker(new MarkerOptions().position(pachacamacMexicano).title("Mexicano - Pachacamac"));
        mMap.addMarker(new MarkerOptions().position(cieneguilla).title("Ovalo de Cieneguilla"));
        mMap.addMarker(new MarkerOptions().position(totoritas).title("Totoritas"));
        mMap.addMarker(new MarkerOptions().position(santaEulalia).title("Santa Eulalia"));
        mMap.addMarker(new MarkerOptions().position(morroSolar).title("Morro Solar"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(morroSolar, 10.0f));
    }
}
