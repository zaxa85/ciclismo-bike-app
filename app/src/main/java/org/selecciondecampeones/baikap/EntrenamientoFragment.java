package org.selecciondecampeones.baikap;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.selecciondecampeones.baikap.http.RestClient;
import org.selecciondecampeones.baikap.model.Entrenamiento;
import org.selecciondecampeones.baikap.util.EntrenamientoListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EntrenamientoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EntrenamientoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntrenamientoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EntrenamientoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EntrenamientoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EntrenamientoFragment newInstance(String param1, String param2) {
        EntrenamientoFragment fragment = new EntrenamientoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private void loadList(final boolean showOldEvents) {

        RequestParams rp = new RequestParams();
        //rp.add("username", "aaa"); rp.add("password", "aaa@123");

        RestClient.get("/api/sportevent", rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                //Log.d("asd", "---------------- this is response : " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {

                // Construct the data source
                ArrayList<Entrenamiento> arrayOfEntrenamientos = new ArrayList<Entrenamiento>();
                // Setting current date
                Calendar cal = Calendar.getInstance();
                Date date = cal.getTime();

                try {

                    /*
                    for (int i = 0; i < timeline.length(); i++) {
                        JSONObject event = null;
                        event = timeline.getJSONObject(i);
                        Evento tempEvento = new Evento(event);


                        if (tempEvento.getFechaFin().before(date) && showOldEvents == true ) {
                            arrayOfEvents.add(tempEvento);

                        } else if (tempEvento.getFechaFin().after(date) && showOldEvents == false) {
                            arrayOfEvents.add(tempEvento);
                        }
                    }
*/

                    cal.add(Calendar.DATE, -3);  // number of days to add
                    date = cal.getTime();

                    Entrenamiento en1 = new Entrenamiento();
                    en1.setId("1");
                    en1.setDescripcion("Descripción 111");
                    en1.setFecha(date);
                    en1.setTipo("I");
                    en1.setEstado(1);
                    en1.setSemana(1);


                    cal.add(Calendar.DATE, 1);  // number of days to add
                    date = cal.getTime();

                    Entrenamiento en2 = new Entrenamiento();
                    en2.setId("2");
                    en2.setDescripcion("Descripción 222");
                    en2.setFecha(date);
                    en2.setTipo("I");
                    en2.setEstado(1);
                    en2.setSemana(1);

                    cal.add(Calendar.DATE, 1);  // number of days to add
                    date = cal.getTime();

                    Entrenamiento en3 = new Entrenamiento();
                    en3.setId("2");
                    en3.setDescripcion("Descripción 222");
                    en3.setFecha(date);
                    en3.setTipo("I");
                    en3.setEstado(1);
                    en3.setSemana(1);


                    cal.add(Calendar.DATE, 1);  // number of days to add
                    date = cal.getTime();

                    Entrenamiento en4 = new Entrenamiento();
                    en4.setId("2");
                    en4.setDescripcion("Descripción 222");
                    en4.setFecha(date);
                    en4.setTipo("I");
                    en4.setEstado(1);
                    en4.setSemana(1);


                    cal.add(Calendar.DATE, 1);  // number of days to add
                    date = cal.getTime();

                    Entrenamiento en5 = new Entrenamiento();
                    en5.setId("2");
                    en5.setDescripcion("Descripción 222");
                    en5.setFecha(date);
                    en5.setTipo("I");
                    en5.setEstado(1);
                    en5.setSemana(1);


                    cal.add(Calendar.DATE, 1);  // number of days to add
                    date = cal.getTime();


                    Entrenamiento en6 = new Entrenamiento();
                    en6.setId("2");
                    en6.setDescripcion("Descripción 222");
                    en6.setFecha(date);
                    en6.setTipo("I");
                    en6.setEstado(1);
                    en6.setSemana(1);

                    cal.add(Calendar.DATE, 1);  // number of days to add
                    date = cal.getTime();

                    Entrenamiento en7 = new Entrenamiento();
                    en7.setId("2");
                    en7.setDescripcion("Descripción 222");
                    en7.setFecha(date);
                    en7.setTipo("I");
                    en7.setEstado(1);
                    en7.setSemana(1);


                    arrayOfEntrenamientos.add(en1);
                    arrayOfEntrenamientos.add(en2);
                    arrayOfEntrenamientos.add(en3);
                    arrayOfEntrenamientos.add(en4);
                    arrayOfEntrenamientos.add(en5);
                    arrayOfEntrenamientos.add(en6);
                    arrayOfEntrenamientos.add(en7);

                    arrayOfEntrenamientos.add(en4);
                    arrayOfEntrenamientos.add(en5);
                    arrayOfEntrenamientos.add(en6);
                    arrayOfEntrenamientos.add(en7);
                    arrayOfEntrenamientos.add(en4);
                    arrayOfEntrenamientos.add(en5);
                    arrayOfEntrenamientos.add(en6);
                    arrayOfEntrenamientos.add(en7);

 /*
                    arrayOfEntrenamientos.add(en7);
                    arrayOfEntrenamientos.add(en7);
                    arrayOfEntrenamientos.add(en7);
                    arrayOfEntrenamientos.add(en7);
                    arrayOfEntrenamientos.add(en7);
                    arrayOfEntrenamientos.add(en7);
                    arrayOfEntrenamientos.add(en7);
                    arrayOfEntrenamientos.add(en7);
                    arrayOfEntrenamientos.add(en7);
*/
                    if (getActivity() != null) {

                        // Create the adapter to convert the array to views
                        EntrenamientoListAdapter adapter = new EntrenamientoListAdapter(getContext(), arrayOfEntrenamientos);

                        // Attach the adapter to a ListView
                        ListView listView = (ListView) getView().findViewById(R.id.list_entrenamientos);
                        listView.setAdapter(adapter);
                        listView.setVerticalScrollBarEnabled(true);
                    }
                    //  } catch (JSONException e) {
                    //   e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entrenamiento, container, false);

        boolean showOldEvents = false;

        loadList(showOldEvents);

        return view;
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
}