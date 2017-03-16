package com.medical.proadoc;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.medical.proadoc.CustomControls.CustomTypefaceSpan;
import com.medical.proadoc.fragments.ChartOne;
import com.medical.proadoc.fragments.ChatsFragment;
import com.medical.proadoc.fragments.EditProfile;
import com.medical.proadoc.fragments.HomeFragment;
import com.medical.proadoc.fragments.NotificationsFragment;
import com.medical.proadoc.fragments.SearchFragment;
import com.medical.proadoc.fragments.ViewChildVaccinationFragment;
import com.medical.proadoc.fragments.ViewMyFamilyMembers;


public class Home extends Activity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static  TextView mTitle;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Transition enterTrans = new Explode();
        getWindow().setEnterTransition(enterTrans);

        Transition returnTrans = new Slide();
        getWindow().setReturnTransition(returnTrans);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
         mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("ProAcDoc");

        setSupportActionBar(toolbar);


        //Show Hide Toolbar Code




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfile fragment = new EditProfile();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                mTitle.setText("Edit Profile");
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {

                }

            }
        });

        HomeFragment fragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();



        Menu m = navigationView.getMenu();
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...

            //the method we have create in activity
            applyFontToMenuItem(mi, Color.BLACK);
        }






    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {

            SearchFragment fragment = new SearchFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            mTitle.setText("Search");

            return true;
        }

        if (id == R.id.actionnotification) {

            NotificationsFragment fragment = new NotificationsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            mTitle.setText("Notifications");

            return true;
        }
        if (id == R.id.actionEmergency) {

         //   Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "198"));
        //    startActivity(intent);

            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.homeNavigation) {
            // Handle the camera action
            HomeFragment fragment = new HomeFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            mTitle.setText("Dashboard");

        } else if (id == R.id.walletNavigation) {
            ChartOne fragment = new ChartOne();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            mTitle.setText("Medical Wallet");

        } else if (id == R.id.notficationNavigation) {
            NotificationsFragment fragment = new NotificationsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            mTitle.setText("Notifications");

        } else if (id == R.id.nav_Chats) {
            ChatsFragment fragment = new ChatsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            mTitle.setText("Chats");



        }

        else if (id == R.id.nav_ChildVaccinations) {
            ViewChildVaccinationFragment fragment = new ViewChildVaccinationFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            mTitle.setText("Child Vaccinations");
        }










        else if (id == R.id.nav_MyFamily) {

            ViewMyFamilyMembers fragment = new ViewMyFamilyMembers();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            mTitle.setText("My Family");

        }
        else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void applyFontToMenuItem(MenuItem mi, int color) {
        Typeface font = Typeface.createFromAsset(getAssets(), "Lato-Regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());

        mNewTitle.setSpan(new CustomTypefaceSpan("", font, color), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        mi.setTitle(mNewTitle);

    }

}
