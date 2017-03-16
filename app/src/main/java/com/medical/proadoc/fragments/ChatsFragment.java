package com.medical.proadoc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.medical.proadoc.AilmentBasedChatsActivity;
import com.medical.proadoc.Home;
import com.medical.proadoc.R;


public class ChatsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

public ImageView imageViewailment,imageViewCity;

    public ChatsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        imageViewailment=(ImageView)getView().findViewById(R.id.imageViewailment);
        imageViewailment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),AilmentBasedChatsActivity.class);
                getActivity().startActivity(i);

            }
        });
        YoYo.with(Techniques.FadeIn).duration(1000).playOn(imageViewailment);

                imageViewCity=(ImageView)getView().findViewById(R.id.imageViewCity);
        imageViewCity .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        YoYo.with(Techniques.FadeIn).duration(1000).playOn(imageViewCity);

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
}
