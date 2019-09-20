package org.selecciondecampeones.baikap;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
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
 * to handle interaction events.
 * Use the {@link CentroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CentroFragment extends Fragment implements OnMapReadyCallback {

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1; // 1 minute
    final int REQUEST_LOCATION = 0;
    private LocationListener locationListener;
    private GoogleMap mMap;
    private LocationManager locationManager;

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

        System.out.println("Entro aqui 1");

        View rootView = inflater.inflate(R.layout.fragment_centro, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.txtCentroMapView);
        //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment

        System.out.println("Entro aqui 2");

        mapFragment.getMapAsync(this);

        System.out.println("Entro aqui 3");

        return rootView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        RequestParams rp = new RequestParams();
        //rp.add("username", "aaa"); rp.add("password", "aaa@123");

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.clear(); //clear old markers

        /*
        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(37.4219999, -122.0862462))
                .zoom(10)
                .bearing(0)
                .tilt(45)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.4219999, -122.0862462))
                .title("Spider Man")
                .icon(bitmapDescriptorFromVector(getActivity(), R.drawable.ic_menu_about)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.4629101, -122.2449094))
                .title("Iron Man")
                .snippet("His Talent : Plenty of money"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.3092293, -122.1136845))
                .title("Captain America"));
*/
        // Zoom into users location
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                centreMapOnLocation(location, "Your Location 1");
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

/*
        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(37.4219999, -122.0862462))
                .zoom(10)
                .bearing(0)
                .tilt(45)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);
*/

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
    }

    public void centreMapOnLocation(Location location, String title) {

        if (location != null) {
            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
            //mMap.clear();
            mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 12));

        } else {
            LatLng trek = new LatLng(-12.105041, -77.0389231);
            mMap.addMarker(new MarkerOptions().position(trek).title("Your Location 2"));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(trek, 15.0f));
        }
    }


    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
