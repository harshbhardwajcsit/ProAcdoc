package com.medical.proadoc.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.medical.proadoc.Home;
import com.medical.proadoc.R;




public class HomeFragment extends Fragment {

    CardView card_view,card_viewSearch,card_viewAddMember,card_viewVaccination;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        card_view=(CardView)getView().findViewById(R.id.card_view);

        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMedicalWalletFragment fragment = new MyMedicalWalletFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                Home.mTitle.setText("Medical Wallet");
            }
        });


        card_viewSearch=(CardView)getView().findViewById(R.id.card_viewSearch);

        card_viewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment fragment = new SearchFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                Home.mTitle.setText("Search");
            }
        });
        card_viewAddMember=(CardView)getView().findViewById(R.id.card_viewAddMember);
       ;
        card_viewAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewMyFamilyMembers fragment = new ViewMyFamilyMembers();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                Home.mTitle.setText("Family Members");

            }
        });




        card_viewVaccination=(CardView)getView().findViewById(R.id.card_viewVaccination);

        card_viewVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewChildVaccinationFragment fragment = new ViewChildVaccinationFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                Home.mTitle.setText("Child Vaccinations");

            }
        });



        super.onActivityCreated(savedInstanceState);
    }
}
