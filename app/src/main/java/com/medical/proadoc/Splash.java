package com.medical.proadoc;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.medical.proadoc.CustomControls.SimpleViewPagerIndicator;
import com.medical.proadoc.fragments.SliderOne;
import com.medical.proadoc.fragments.Sliderthree;
import com.medical.proadoc.fragments.Slidertwo;

public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        SimpleViewPagerIndicator pageIndicator = (SimpleViewPagerIndicator) findViewById(R.id.page_indicator);


        pageIndicator.setViewPager(pager);
        pageIndicator.notifyDataSetChanged();

       /* new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {


                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        Splash.this, image, "MainLogo");
                ActivityCompat.startActivity(Splash.this, new Intent(Splash.this, Login.class),
                        options.toBundle());


            }
        }, SPLASH_TIME_OUT);*/
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return SliderOne.newInstance();
                case 1: return Slidertwo.newInstance();
                case 2: return Sliderthree.newInstance();

                default:return SliderOne.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
    public void skip_click(View v)
    {

        Intent i=new Intent(Splash.this,Login.class);
        startActivity(i);
    }
}
