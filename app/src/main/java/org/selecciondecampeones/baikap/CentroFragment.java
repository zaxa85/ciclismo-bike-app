package org.selecciondecampeones.baikap;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CentroFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CentroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CentroFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private MapView mapView;
    private GoogleMap mMap;

    private OnFragmentInteractionListener mListener;

    public CentroFragment() {
        // Required empty public constructor
    }

    public static CentroFragment newInstance(String param1, String param2) {
        CentroFragment fragment = new CentroFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_centro, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapView2);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this); //this is important

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    final int REQUEST_LOCATION = 0;


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*
        int REQUEST_CODE_A = 1;
        if (ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(  new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_A);
        }
        */

        // Add a marker in Sydney and move the camera
        LatLng trek = new LatLng(-12.105041, -77.0389231);
        LatLng gbbikes = new LatLng( -12.1229818,-77.0259343);
        LatLng specialized = new LatLng(-12.1287301,-77.0238403);
        LatLng santacruz = new LatLng(-12.11099,-77.0380666);
        LatLng bestbikes = new LatLng(-12.1086603,-77.0352661);
        LatLng bicicentro = new LatLng( -12.1152292,-77.0268839);
        LatLng kenirobike = new LatLng(-12.073482,-76.9798997);
        LatLng zonacanicoba = new LatLng( -12.1017584,-77.0586911);
        LatLng cecilVillagarcia = new LatLng( -12.0875906,-77.0520955);
        LatLng jesusnakada = new LatLng(-12.0170987,-76.8858929);

        mMap.addMarker(new MarkerOptions().position(trek).title("Tienda Trek").snippet("Tienda de ciclismo especializada.\nMarcas Trek, Bontrager"));
        mMap.addMarker(new MarkerOptions().position(gbbikes).title("Tienda GB Bikes").snippet("Tienda de ciclismo especializada.\nMarcas BMC, Colnago"));
        mMap.addMarker(new MarkerOptions().position(specialized).title("Tienda Specialized - Reducto"));
        mMap.addMarker(new MarkerOptions().position(santacruz).title("Tienda Santa Cruz"));
        mMap.addMarker(new MarkerOptions().position(bestbikes).title("Tienda Best Bikes"));
        mMap.addMarker(new MarkerOptions().position(bicicentro).title("Tienda Bicicentro Miraflores"));
        mMap.addMarker(new MarkerOptions().position(kenirobike).title("Taller Keniro Bike"));
        mMap.addMarker(new MarkerOptions().position(zonacanicoba).title("Taller Zona Técnica Canicova"));
        mMap.addMarker(new MarkerOptions().position(cecilVillagarcia).title("Técnico Especializado: Cecil Villagarcia"));
        mMap.addMarker(new MarkerOptions().position(jesusnakada).title("Técnico Especializado: Jesús Nakada"));


        LatLng pachacamacMexicano = new LatLng(-12.2030146, -76.8490809);
        LatLng cieneguilla = new LatLng( -12.1185067, -76.8161714);
        LatLng totoritas = new LatLng(-12.6787868, -76.6530128);
        LatLng santaEulalia = new LatLng(-11.9028264, -76.6674042);
        LatLng morroSolar = new LatLng(-12.1886629, -77.0362636);
        LatLng pentagonito = new LatLng(-12.1007894, -76.9875752);
        LatLng ecologico = new LatLng(-12.1181388, -76.9317411);

        mMap.addMarker(new MarkerOptions().position(pachacamacMexicano).title("Mexicano - Pachacamac"));
        mMap.addMarker(new MarkerOptions().position(cieneguilla).title("Ovalo de Cieneguilla"));
        mMap.addMarker(new MarkerOptions().position(totoritas).title("Totoritas"));
        mMap.addMarker(new MarkerOptions().position(santaEulalia).title("Santa Eulalia"));
        mMap.addMarker(new MarkerOptions().position(morroSolar).title("Morro Solar"));
        mMap.addMarker(new MarkerOptions().position(pentagonito).title("Pentagonito"));
        mMap.addMarker(new MarkerOptions().position(ecologico).title("Parque Ecológico"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(trek, 15.0f));
        mMap.setOnMarkerClickListener(this);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        View v = getView();
        TextView textView = (TextView) v.findViewById(R.id.textView7);
        textView.setText(marker.getTitle() + "\n\n" + marker.getSnippet() != null ?  marker.getSnippet() : "" );
        return false;
    }
}
