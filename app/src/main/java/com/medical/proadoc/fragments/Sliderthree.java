package com.medical.proadoc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medical.proadoc.R;


public class Sliderthree extends Fragment {


    public static Sliderthree newInstance(){


        return new Sliderthree();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sliderthree, container, false);
    }




}
