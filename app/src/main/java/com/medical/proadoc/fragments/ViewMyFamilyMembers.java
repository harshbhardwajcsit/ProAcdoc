package com.medical.proadoc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.medical.proadoc.Adaptors.MedicalWalletRecordsRecyclerViewAdapter;
import com.medical.proadoc.Adaptors.MyFamilyMembersRecyclerViewAdapter;
import com.medical.proadoc.AddNewFamilyMember;
import com.medical.proadoc.HelperClasses.ApplicationGlobles;
import com.medical.proadoc.HelperClasses.Constaints;
import com.medical.proadoc.Home;
import com.medical.proadoc.Models.FamilyMembersModel;
import com.medical.proadoc.Models.MedicalRecord;
import com.medical.proadoc.Models.RelationModel;
import com.medical.proadoc.R;
import com.medical.proadoc.Volley.MyJsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ViewMyFamilyMembers extends Fragment {

    public ProgressBar progressBar;
    private View LayoutView;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    public com.melnykov.fab.FloatingActionButton floatingActionButton;
    private List<FamilyMembersModel> mContentItems = new ArrayList<FamilyMembersModel>(0);
    private String TAG = "ViewMyFamilyMembers";

    private static RequestQueue mRequestQueue;
    public LinearLayout lvEmpty;

    public ViewMyFamilyMembers() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_my_family_members, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        progressBar = (ProgressBar)getView(). findViewById(R.id.progressBar);
        lvEmpty= (LinearLayout)getView(). findViewById(R.id.lvEmpty);
        lvEmpty.setVisibility(View.GONE);
        LayoutView =getView(). findViewById(R.id.snackbarPosition);
        mRecyclerView = (RecyclerView)getView(). findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MyFamilyMembersRecyclerViewAdapter(mContentItems,getActivity());
        mRecyclerView.setAdapter(mAdapter);


















        floatingActionButton= (com.melnykov.fab.FloatingActionButton  )getView(). findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i=new Intent(getActivity(), AddNewFamilyMember.class);
                 getActivity().startActivity(i);

            }
        });
        floatingActionButton.attachToRecyclerView(mRecyclerView);





        super.onActivityCreated(savedInstanceState);
    }
  public void  loadMyFamilyMembers()
  {
      if (ApplicationGlobles.isConnectingToInternet(getActivity())) {



          String URL = Constaints.GetAllMembers;


          showProgressDialog();
          Map<String,String> params = new HashMap<String,String>();



          processGetRequest(URL, params, new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {
                  Log.d("Swasthy", response.toString());
                  //  Toast.makeText(getActivity(), "go" + response.toString(), Toast.LENGTH_LONG).show();

                  try {


                      JSONArray familyMemberJson = response.getJSONArray("Members");

                      mContentItems.clear();
                      for (int i = 0; i < familyMemberJson.length(); i++) {
                          FamilyMembersModel model = new FamilyMembersModel();
                          JSONObject FamilyMembersJson = familyMemberJson.getJSONObject(i);



                          model.setDOB(FamilyMembersJson.optString("DOB"));


                          model.setName(FamilyMembersJson.optString("Name"));
                          model.setMemberId(FamilyMembersJson.optInt("MemberId"));


                          mContentItems.add(i, model);


                      }
                      View  snackbarPosition=getView().findViewById(R.id.snackbarPosition);
                      Snackbar.make(snackbarPosition,"You Have Total "+familyMemberJson.length()+" Family Members Added", Snackbar.LENGTH_LONG).show();
                      mAdapter.notifyDataSetChanged();
                      if(mContentItems.size()==0)
                      {

                          lvEmpty.setVisibility(View.VISIBLE);
                          mRecyclerView.setVisibility(View.GONE);
                      }





                  } catch (JSONException e) {

                  }





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
        loadMyFamilyMembers();

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

    protected MyJsonRequest processGetRequest(String requestUrl, Map<String, String> params,
                                               Response.Listener<JSONObject> responseListener,
                                               Response.ErrorListener errorListener, String Tag, boolean shouldCache) {
        MyJsonRequest request = new MyJsonRequest(getActivity(), Request.Method.GET, requestUrl
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
