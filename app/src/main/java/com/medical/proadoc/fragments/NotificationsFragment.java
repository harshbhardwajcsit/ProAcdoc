package com.medical.proadoc.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.medical.proadoc.Adaptors.NotificationsRecyclerViewAdapter;
import com.medical.proadoc.HelperClasses.ApplicationGlobles;
import com.medical.proadoc.HelperClasses.Constaints;
import com.medical.proadoc.Home;
import com.medical.proadoc.Models.NotificationModel;
import com.medical.proadoc.R;
import com.medical.proadoc.Volley.MyJsonRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NotificationsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<NotificationModel> mContentItems = new ArrayList<NotificationModel>();

    public ProgressBar progressBar;
    private View LayoutView;
    private String TAG = "NotificationsFragment";

    private static RequestQueue mRequestQueue;
    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }
    private void findViewsById() {

        progressBar = (ProgressBar)getView(). findViewById(R.id.progressBar);


        LayoutView =getView(). findViewById(R.id.snackbarPosition);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView)getView(). findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new NotificationsRecyclerViewAdapter(mContentItems);
        mRecyclerView.setAdapter(mAdapter);

        {
            for (int i = 0; i < NotificationModel.GetDummyTopicOfTheMonth().size(); ++i)
                mContentItems.add(NotificationModel.GetDummyTopicOfTheMonth().get(i));
            mAdapter.notifyDataSetChanged();
        }
        super.onActivityCreated(savedInstanceState);
    }

    public void loadMyPushNotifications()
    {

        if (ApplicationGlobles.isConnectingToInternet(getActivity())) {



                String URL = Constaints.LoginUrl;


                showProgressDialog();
                Map<String,String> params = new HashMap<String,String>();
                params.put("grant_type", "password");
                params.put("client_id", "SwasthyAndroidApp");


                processPostRequest(URL, params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Swasthy", response.toString());
                        Toast.makeText(getActivity().getApplicationContext(), "go" + response.toString(), Toast.LENGTH_LONG).show();


                        hideProgressDialog();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                        hideProgressDialog();
                    }
                }, "Test", false);





        } else {
            Snackbar.make(LayoutView, getResources().getString(R.string.InternetError), Snackbar.LENGTH_LONG).setAction("Check Settings", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(
                            new Intent(Settings.ACTION_SETTINGS));
                }
            }).show();

        }
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
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
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
}
