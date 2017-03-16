package com.medical.proadoc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.medical.proadoc.Adaptors.MedicalServicesRecyclerViewAdapter;
import com.medical.proadoc.Models.MedicalServicesProviderModel;
import com.medical.proadoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment  {

    public SearchFragment() {
        // Required empty public constructor
    }


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    public com.melnykov.fab.FloatingActionButton floatingActionButton;
    private List<MedicalServicesProviderModel> mContentItems = new ArrayList<MedicalServicesProviderModel>(0);
    private String TAG = "ViewMyFamilyMembers";
    private View LayoutView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LayoutView =getView(). findViewById(R.id.snackbarPosition);


        LayoutView =getView(). findViewById(R.id.snackbarPosition);
        mRecyclerView = (RecyclerView)getView(). findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MedicalServicesRecyclerViewAdapter(mContentItems,getActivity());
        mRecyclerView.setAdapter(mAdapter);


      //  loadJSONFromAsset();

for(int i = 0; i < MedicalServicesProviderModel.getDummyData(getActivity()).size(); i++)


{

    mContentItems.add(i,MedicalServicesProviderModel.getDummyData(getActivity()).get(i));
    mAdapter.notifyDataSetChanged();

}


    }


}
