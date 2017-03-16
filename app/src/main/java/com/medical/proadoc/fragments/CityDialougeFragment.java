package com.medical.proadoc.fragments;

/**
 * Created by HPpc on 1/19/2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;


import com.medical.proadoc.Adaptors.CityAdapter;
import com.medical.proadoc.HelperClasses.Constaints;
import com.medical.proadoc.Login;
import com.medical.proadoc.Models.CityModel;
import com.medical.proadoc.R;
import com.medical.proadoc.Volley.MyJsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityDialougeFragment extends DialogFragment {

    TextView tvDialougeHeader;
    private RecyclerView rvCountryList;
    private RecyclerView.Adapter mAdapter;
    private List<CityModel> mContentItems = new ArrayList<CityModel>();
    private String TAG = Login.class.getSimpleName();

    private static RequestQueue mRequestQueue;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialouge_fragment, container,
                false);
        // Do something else


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDialougeHeader = (TextView) view.findViewById(R.id.tvDialougeHeader);

        rvCountryList = (RecyclerView) view.findViewById(R.id.rvCountryList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvCountryList.setLayoutManager(layoutManager);
        rvCountryList.setHasFixedSize(true);
        loadCity();






    }
    public void loadCity()
    {
        String URL = Constaints.LoadCities;




        Map<String, String> params = new HashMap<String, String>();




        processGetRequest(URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Swasthy", response.toString());


try {



JSONArray cityArray=response.getJSONArray("Items");


    for(int i=0;i<cityArray.length();i++)
    {
        CityModel model=new CityModel();
        JSONObject city=cityArray.getJSONObject(i);
        model.setCityId(city.optInt("Key"));
        model.setCityName(city.optString("Value"));

        mContentItems.add(i,model);

    }

    mAdapter = new CityAdapter(mContentItems, getActivity().getApplicationContext());
    rvCountryList.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();

}
catch (JSONException e)
{

}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_LONG).show();

            }
        }, "Test", false);



    }
    protected <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(tag);
        getRequestQueue().add(req);
    }
    protected RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getActivity());
        }
        return mRequestQueue;
    }

    protected MyJsonRequest processGetRequest(String requestUrl, Map<String, String> params,
                                              Response.Listener<JSONObject> responseListener,
                                              Response.ErrorListener errorListener, String Tag, boolean shouldCache) {
        MyJsonRequest request = new MyJsonRequest(getActivity(), Request.Method.GET, requestUrl
                , params, responseListener, errorListener);
        request.setShouldCache(shouldCache);
        addToRequestQueue(request, Tag);
        return request;
    }
}
