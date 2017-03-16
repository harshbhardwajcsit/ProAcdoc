package com.medical.proadoc.fragments;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.medical.proadoc.Adaptors.MedicalWalletRecordsRecyclerViewAdapter;
import com.medical.proadoc.AddMedicalReport;
import com.medical.proadoc.HelperClasses.Constaints;
import com.medical.proadoc.Home;
import com.medical.proadoc.Models.FamilyMembersModel;
import com.medical.proadoc.Models.MedicalRecord;
import com.medical.proadoc.R;
import com.medical.proadoc.Volley.MyJsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class MyMedicalWalletFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
public com.melnykov.fab.FloatingActionButton floatingActionButton;
    private List<MedicalRecord> mContentItems = new ArrayList<MedicalRecord>();
    public ProgressBar progressBar;
    private View LayoutView;
    private String TAG = "MyMedicalWalletFragment";

    private static RequestQueue mRequestQueue;
    public MyMedicalWalletFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_medical_wallet, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        progressBar = (ProgressBar)getView(). findViewById(R.id.progressBar);
        LayoutView = getView().findViewById(R.id.snackbarPosition);
        mRecyclerView = (RecyclerView)getView(). findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);


        mAdapter = new MedicalWalletRecordsRecyclerViewAdapter(mContentItems,getActivity());
        mRecyclerView.setAdapter(mAdapter);




        //Laod My Medical Wallet Records Here
        String URL = Constaints.GetAllReport;


        showProgressDialog();
        Map<String,String> params = new HashMap<String,String>();




        processGetRequest(URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Swasthy", response.toString());
                //       Toast.makeText(getActivity(), "go" + response.toString(), Toast.LENGTH_LONG).show();'


                try {


                    JSONArray ReportsArray = response.getJSONArray("Reports");

                    mContentItems.clear();
                    for (int i = 0; i < ReportsArray.length(); i++) {
                        MedicalRecord model = new MedicalRecord();
                        JSONObject medicalRecordJson = ReportsArray.getJSONObject(i);
                        model.setAilment(medicalRecordJson.optString("Ailment"));
                        model.setDate(medicalRecordJson.optString("CreatedDate"));
                        model.setDoctorName(medicalRecordJson.optString("DiagnosedBy"));
                        model.setName(medicalRecordJson.optString("Name"));
                        model.setMedicalRecordId(medicalRecordJson.optInt("ReportId"));
                        mContentItems.add(i, model);


                    }
                    View snackbarPosition = getView().findViewById(R.id.snackbarPosition);
                    Snackbar.make(snackbarPosition, "You Have Total " + ReportsArray.length() + " Docs Added", Snackbar.LENGTH_LONG).show();
                    mAdapter.notifyDataSetChanged();
                  /*  if (mContentItems.size() == 0) {

                        lvEmpty.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                    }*/


                } catch (JSONException e) {

                }


                hideProgressDialog();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
                hideProgressDialog();
            }
        }, "Test", false);














        floatingActionButton= (com.melnykov.fab.FloatingActionButton  )getView(). findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(getActivity(),AddMedicalReport.class);
                getActivity().startActivity(i);
            }
        });
        floatingActionButton.attachToRecyclerView(mRecyclerView);













        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    // handle back button



                    HomeFragment fragment = new HomeFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =getActivity(). getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                    Home.mTitle.setText("Dashboard");


                    return true;

                }

                return false;
            }
        });
    }
    private void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);

    }

    private void hideProgressDialog() {
        progressBar.setVisibility(View.GONE);
    }

    protected MyJsonRequest processPostRequest(String requestUrl, Map<String, String> params,
                                               Response.Listener<JSONObject> responseListener,
                                               Response.ErrorListener errorListener, String Tag, boolean shouldCache) {
        MyJsonRequest request = new MyJsonRequest(getActivity(), Request.Method.POST, requestUrl
                , params, responseListener, errorListener);
        request.setShouldCache(shouldCache);
        addToRequestQueue(request, Tag);
        return request;
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
