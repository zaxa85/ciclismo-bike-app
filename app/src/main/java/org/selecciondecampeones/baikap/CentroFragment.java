package org.selecciondecampeones.baikap;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.selecciondecampeones.baikap.http.RestClient;
import org.selecciondecampeones.baikap.model.Lugar;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CentroFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CentroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CentroFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1; // 1 minute
    final int REQUEST_LOCATION = 0;
    private MapView mapView;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
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
        View currentView = inflater.inflate(R.layout.fragment_centro, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) currentView.findViewById(R.id.txtCentroMapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this); //this is important

        return currentView;
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

        /*
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

        mMap.setOnMarkerClickListener(this);
/*/

        RequestParams rp = new RequestParams();
        //rp.add("username", "aaa"); rp.add("password", "aaa@123");

        RestClient.get("/api/location", rp, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {

                try {
                    for (int i = 0; i < timeline.length(); i++) {
                        JSONObject event = null;
                        event = timeline.getJSONObject(i);
                        Lugar tempLugar = new Lugar(event);

                        LatLng makerLocation = new LatLng(tempLugar.getLongitud(), tempLugar.getLatitud());

                        MarkerOptions makerOpt = new MarkerOptions();
                        makerOpt.position(makerLocation);
                        makerOpt.title(tempLugar.getTitulo());
                        makerOpt.snippet(tempLugar.getDescripción());

                        mMap.addMarker(makerOpt);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        // Zoom into users location
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                centreMapOnLocation(location, "Your Location");
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };


        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            centreMapOnLocation(lastKnownLocation, "Mi ubicación");



        } else {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

    }

    /**
     * Called when the user clicks a marker.
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        View currentView = getView();
        TextView textView = (TextView) currentView.findViewById(R.id.txtCentroDetalle);
        textView.setText(marker.getTitle() + "\n\n" + marker.getSnippet() != null ? marker.getSnippet() : "");
        return true;
    }

    public void centreMapOnLocation(Location location, String title) {

        if (location != null ) {
            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 12));

        }
/*
        LatLng trek = new LatLng(-12.105041, -77.0389231);
        mMap.addMarker(new MarkerOptions().position(trek).title("Your Location"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(trek, 15.0f));
        */

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

    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centreMapOnLocation(lastKnownLocation,"Your Location");
            }
        }
    }
    */
}
