package com.medical.proadoc.fragments;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.medical.proadoc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class SelectBodyPartFragment extends DialogFragment   {
ImageView imageView4;

LinearLayout addHere;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.selectbodypartrlayout, container, false);


























        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        addHere=(LinearLayout)getView().findViewById(R.id.addHere);
        imageView4=(ImageView)getView().findViewById(R.id.imageView4);
        imageView4.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
               ImageView ImageTouched=new ImageButton(getActivity().getApplicationContext());
                ImageTouched.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.tick));
                addHere.addView(ImageTouched);
                return true;
            }
        });

    }


}
