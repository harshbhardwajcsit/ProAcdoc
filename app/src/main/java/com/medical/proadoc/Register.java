package com.medical.proadoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import com.medical.proadoc.Models.UserModel;
import com.medical.proadoc.Volley.MyJsonRequest;
import com.medical.proadoc.fragments.CityDialougeFragment;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends Activity implements DatePickerDialog.OnDateSetListener {

    public EditText editTextEmail,editTextPassword,editTextMobile,editTextDOB,editTextName;;
    public FloatingActionButton floatingActionButton;
    private String TAG = Login.class.getSimpleName();
    public static EditText editTextCity;
    private static RequestQueue mRequestQueue;
    public ProgressBar progressBar;
    private View LayoutView;
    FragmentManager fm = getSupportFragmentManager();
    public static CityDialougeFragment dFragment;
    public static int CityId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dFragment = new CityDialougeFragment();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Register With ProAcDoc");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.backarrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        findViewsById();


    }
    public void findViewsById()
    {

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        LayoutView = findViewById(R.id.snackbarPosition);
        editTextName=(EditText)findViewById(R.id.editTextName);
      //  editTextName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.username, 0, 0, 0);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
     //   editTextEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.username, 0, 0, 0);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
     //   editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password,0,0,0);
        editTextMobile=(EditText)findViewById(R.id.editTextMobile);
     //   editTextMobile.setCompoundDrawablesWithIntrinsicBounds(R.drawable.phone,0,0,0);
        editTextDOB=(EditText)findViewById(R.id.editTextDOB);
        editTextDOB.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dob, 0, 0, 0);
        editTextDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Register.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        editTextCity=(EditText)findViewById(R.id.editTextCity);
      //  editTextCity.setCompoundDrawablesWithIntrinsicBounds(R.drawable.city, 0, 0, 0);
        editTextCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dFragment.show(fm, "");
            }
        });

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ApplicationGlobles.isConnectingToInternet(Register.this)) {

                    if (checkValidation()) {

                        String URL = Constaints.RegistrationUrl;

                        UserModel userModel = new UserModel();

                        showProgressDialog();
                        Map<String, String> params = new HashMap<String,String>();


                        params.put("Email", editTextEmail.getText().toString().trim());
                        params.put("Name", editTextName.getText().toString().trim());
                        params.put("Password", editTextPassword.getText().toString().trim());
                        params.put("Mobile", editTextMobile.getText().toString().trim());
                        params.put("ConfirmPassword", editTextPassword.getText().toString().trim());


                        processPostRequest(URL, params, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Swasthy", response.toString());
                               // Toast.makeText(getBaseContext(), "go" + response.toString(), Toast.LENGTH_LONG).show();

                        int responceCode=response.optInt("Identity");
                                if(responceCode==0)
                                {
                                    try {
                                        JSONObject result = response.getJSONObject("Result");
                                        JSONArray arrauerrors=result.getJSONArray("Errors");
                                        Snackbar.make(LayoutView, arrauerrors.getString(0).toString(), Snackbar.LENGTH_LONG).show();
                                    }
                                    catch(JSONException e)
                                    {

                                    }

                                }
                                else

                                {
                                    Snackbar.make(LayoutView, "Registration Successfull ! Please Login", Snackbar.LENGTH_LONG).show();

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
        });


    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        editTextDOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
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
    private boolean checkValidation() {
        boolean ret = true;

        if (!RegexValiations.hasText(editTextName))
            ret = false;

        if (!RegexValiations.hasText(editTextEmail))
            ret = false;

        if (!RegexValiations.hasText(editTextPassword))
            ret = false;
        if (!RegexValiations.hasText(editTextMobile))
            ret = false;



        return ret;
    }





}
