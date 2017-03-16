package com.medical.proadoc;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.medical.proadoc.HelperClasses.ApplicationGlobles;
import com.medical.proadoc.HelperClasses.Constaints;
import com.medical.proadoc.HelperClasses.RegexValiations;
import com.medical.proadoc.HelperClasses.SessionManager;
import com.medical.proadoc.Volley.MyJsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends Activity {
    public ProgressBar progressBar;
    private View LayoutView;
    //Login Controls
    public EditText editTextLoginUserName, editTextLoginPassword;
    public Toolbar toolbar;
    private String TAG = Login.class.getSimpleName();

    private static RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        Transition exitTrans = new Slide();
        getWindow().setExitTransition(exitTrans);

        Transition reenterTrans = new Fade();
        getWindow().setReenterTransition(reenterTrans);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView   mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Login With ProAcDoc");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        findViewsById();


    }
    private void findViewsById() {

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextLoginUserName = (EditText) findViewById(R.id.editTextLoginUserName);
      //  editTextLoginUserName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.username,0,0,0);
        editTextLoginPassword = (EditText) findViewById(R.id.editTextLoginPassword);
     //   editTextLoginPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password, 0, 0, 0);

        LayoutView = findViewById(R.id.snackbarPosition);
    }

    public void loginNow(View v)
    {


        InputMethodManager inputManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        if (ApplicationGlobles.isConnectingToInternet(Login.this)) {

            if (checkValidation()) {

                String URL = Constaints.LoginUrl;


                showProgressDialog();
                Map<String,String> params = new HashMap<String,String>();
                params.put("grant_type", "password");
                params.put("client_id", "SwasthyAndroidApp");
                params.put("username", editTextLoginUserName.getText().toString().trim());
                params.put("password", editTextLoginPassword.getText().toString().trim());


                processPostRequest(URL, params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Swasthy", response.toString());

                        try {
                            JSONObject userJson = new JSONObject(new String(response.toString()));


                            SessionManager.setAuthToken(getApplicationContext(), userJson.optString("access_token"));


                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this);
                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent, options.toBundle());
                        }
                        catch (JSONException e)
                        {

                        }

                        hideProgressDialog();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "error", Toast.LENGTH_LONG).show();
                        hideProgressDialog();
                    }
                }, "Test", false);



            }

        } else
            Snackbar.make(LayoutView, getResources().getString(R.string.InternetError), Snackbar.LENGTH_LONG).setAction("Check Settings", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(
                            new Intent(Settings.ACTION_SETTINGS));
                }
            }).show();



    }

    public void saveUserDetails(JSONObject UserInfo) {
        try {
            SessionManager.setUID(getApplicationContext(), UserInfo.getString("user_id"));
            SessionManager.setEmailAddress(getApplicationContext(), UserInfo.getString("user_email_id"));
            SessionManager.setFullName(getApplicationContext(), UserInfo.getString("user_name"));
            SessionManager.setRefererCode(getApplicationContext(), UserInfo.getString("user_referral_code"));
            JSONObject UserCountry=UserInfo.getJSONObject("country_details");
            SessionManager.setCountryId(getApplicationContext(), UserCountry.getString("country_id"));

            if(ApplicationGlobles.isNullOrEmpty(UserInfo.getString("user_designation")))
            {

                SessionManager.setDesignation(getApplicationContext(), "");
            }
            else

            {
                SessionManager.setDesignation(getApplicationContext(), UserInfo.getString("user_designation"));
            }


            SessionManager.setImage(getApplicationContext(), UserInfo.getString("user_image_path"));
            SessionManager.setPhoneNumber(getApplicationContext(), UserInfo.getString("user_phone_number"));





            if (UserInfo.optString("user_work_place") != null && ! UserInfo.optString("user_work_place").isEmpty()) {
                // do whatever you want
                SessionManager.setWorkPlace(getApplicationContext(), UserInfo.getString("user_work_place"));
            } else {
                // the string received is null
                SessionManager.setWorkPlace(getApplicationContext(), "");
            }

            SessionManager.setAuthToken(getApplicationContext(), UserInfo.getString("user_auth_token"));
            SessionManager.setRole(getApplicationContext(), UserInfo.optString("user_role"));
        }
        catch (Exception e) {

        }
    }


    private boolean checkValidation() {
        boolean ret = true;

        if (!RegexValiations.hasText(editTextLoginUserName))
            ret = false;

        if (!RegexValiations.hasText(editTextLoginPassword))
            ret = false;



        return ret;
    }

    public void forgot_click(View v)
    {

        Intent i=new Intent(Login.this,ForgotPassword.class);
        startActivity(i);
    }

    public void register_click(View v)
    {

        Intent i=new Intent(Login.this,Register.class);
        startActivity(i);
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
        MyJsonRequest request = new MyJsonRequest(this, Request.Method.POST, requestUrl
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
            mRequestQueue = Volley.newRequestQueue(this);
        }
        return mRequestQueue;
    }
}
