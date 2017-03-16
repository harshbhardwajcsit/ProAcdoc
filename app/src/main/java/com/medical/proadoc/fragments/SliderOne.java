package com.medical.proadoc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medical.proadoc.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SliderOne#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SliderOne extends Fragment {



    public static  SliderOne newInstance(){


        return new SliderOne();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slider_one, container, false);
    }

}
