package org.selecciondecampeones.baikap;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.selecciondecampeones.baikap.http.RestClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TalleresFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TalleresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TalleresFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayAdapter<String> itemsAdapter;

    private OnFragmentInteractionListener mListener;

    public TalleresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TalleresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TalleresFragment newInstance(String param1, String param2) {
        TalleresFragment fragment = new TalleresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        RequestParams rp = new RequestParams();
        //rp.add("username", "aaa"); rp.add("password", "aaa@123");

        RestClient.get("/api/workshop", rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                //Log.d("asd", "---------------- this is response : " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());

                    System.out.println(" single" + serverResp.toString());

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                List<String> allNames = new ArrayList<>();
                try {
                    for (int i = 0; i < timeline.length(); i++) {
                        JSONObject event = null;

                        String name = null;
                        String address = null;
                        String location = null;
                        String services = "";
                        StringBuilder row = new StringBuilder();
                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                        StringTokenizer token = null;
                        event = timeline.getJSONObject(i);

                        try {
                            name = event.getString("name");
                            address = event.getString("address");
                            location = event.getString("location");

                            token = new StringTokenizer(event.getString("services"), "|");

                            while (token.hasMoreElements()) {
                                services += "\n-" + token.nextToken().trim();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        row.append(name)
                                .append("\nDirección: ").append(address)
                                .append("\nLugar: ").append(location)
                                .append("\nServicios:").append(services);

                        allNames.add(row.toString());
                    }

                    itemsAdapter = new ArrayAdapter<String>(getContext(), R.layout.eventos_list_view, R.id.eventoTextView, allNames);
                    ListView listView = (ListView) getView().findViewById(R.id.list_talleres);
                    listView.setAdapter(itemsAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_talleres, container, false);
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
            //    + " must implement OnFragmentInteractionListener");
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
