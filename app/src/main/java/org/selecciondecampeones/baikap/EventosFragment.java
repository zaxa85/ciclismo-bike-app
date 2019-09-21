package org.selecciondecampeones.baikap;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.selecciondecampeones.baikap.http.RestClient;
import org.selecciondecampeones.baikap.model.Evento;
import org.selecciondecampeones.baikap.util.EventoListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventosFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> itemsAdapter;
    private OnFragmentInteractionListener mListener;
    private ToggleButton toggleButton1;
    private List<Evento> listaEventos = null;

    public EventosFragment() {
        listaEventos = new ArrayList<Evento>();
    }

    public static EventosFragment newInstance(String param1, String param2) {
        EventosFragment fragment = new EventosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         loadListData(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eventos, container, false);

        toggleButton1 = (ToggleButton) view.findViewById(R.id.toggleButtonEvent);
        toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            boolean showOldEvents = false;

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    showOldEvents = true;
                } else {
                    showOldEvents = false;
                }

                loadListValues(showOldEvents);
            }
        });

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
        } else {
            //throw new RuntimeException(context.toString()
            //     + " must implement OnFragmentInteractionListener");
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


    private void loadListData(final boolean showOldEvents) {

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
                List<Evento> arrayOfEvents = new ArrayList<Evento>();
                // Setting current date
                Calendar cal = Calendar.getInstance();
                Date date = cal.getTime();

                try {
                    for (int i = 0; i < timeline.length(); i++) {
                        JSONObject event = timeline.getJSONObject(i);
                        Evento tempEvento = new Evento(event);
                        listaEventos.add(tempEvento);

                        if (tempEvento.getFechaFin().before(date) && showOldEvents == true) {
                            arrayOfEvents.add(tempEvento);

                        } else if (tempEvento.getFechaFin().after(date) && showOldEvents == false) {
                            arrayOfEvents.add(tempEvento);
                        }
                    }

                    Collections.sort(arrayOfEvents);

                    if (getActivity() != null) {
                        // Create the adapter to convert the array to views
                        EventoListAdapter adapter = new EventoListAdapter(getContext(), arrayOfEvents);

                        // Attach the adapter to a ListView
                        ListView listView = (ListView) getView().findViewById(R.id.list_eventos);
                        listView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void loadListValues(final boolean showOldEvents) {

        if (toggleButton1 != null) {
            toggleButton1.setEnabled(false);
        }
        // Construct the data source
        List<Evento> arrayOfEvents = new ArrayList<Evento>();
        // Setting current date
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        for (int i = 0; i < listaEventos.size(); i++) {
            Evento tempEvento = listaEventos.get(i);

            if (tempEvento.getFechaFin().before(date) && showOldEvents == true) {
                arrayOfEvents.add(tempEvento);

            } else if (tempEvento.getFechaFin().after(date) && showOldEvents == false) {
                arrayOfEvents.add(tempEvento);
            }
        }

        Collections.sort(arrayOfEvents);

        if (getActivity() != null) {
            // Create the adapter to convert the array to views
            EventoListAdapter adapter = new EventoListAdapter(getContext(), arrayOfEvents);

            // Attach the adapter to a ListView
            ListView listView = (ListView) getView().findViewById(R.id.list_eventos);
            listView.setAdapter(adapter);

        }
        if (toggleButton1 != null) {
            toggleButton1.setEnabled(true);
        }
    }
}