package com.medical.proadoc;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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
import com.cocosw.bottomsheet.BottomSheet;

import com.medical.proadoc.Adaptors.AddNewMemberCityAdapter;
import com.medical.proadoc.HelperClasses.ApplicationGlobles;
import com.medical.proadoc.HelperClasses.Constaints;
import com.medical.proadoc.HelperClasses.RegexValiations;
import com.medical.proadoc.HelperClasses.SessionManager;
import com.medical.proadoc.Models.CityModel;
import com.medical.proadoc.Models.RelationModel;
import com.medical.proadoc.Volley.MyJsonRequest;
import com.medical.proadoc.fragments.AddNewMemberCityDialougeFragment;
import com.medical.proadoc.fragments.AddNewVacciantionFragment;
import com.medical.proadoc.fragments.CityDialougeFragment;
import com.medical.proadoc.fragments.MyMedicalWalletFragment;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewFamilyMember extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public EditText editTextEmail,editTextPassword,editTextMobile,editTextDOB,editTextRelation,editTextName;
    public FloatingActionButton floatingActionButton;
    public ProgressBar progressBar;
    private View LayoutView;
    private String TAG = AddNewFamilyMember.class.getSimpleName();
    public static EditText editTextCity;

    private static RequestQueue mRequestQueue;
    public static  RelationModel relationModel;


    FragmentManager fm = getSupportFragmentManager();
    public static AddNewMemberCityDialougeFragment dFragment;
    public static int CityId = -1;
    private static int TIME_OUT = 2000;

    private ArrayList<RelationModel> relationModelList=new ArrayList<RelationModel>(0);
    BottomSheet.Builder relationbbottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewfamilymember);
        dFragment = new AddNewMemberCityDialougeFragment();
        findViewsById();
        loadRelations();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Add New Member");

        toolbar.inflateMenu(R.menu.menu_main);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.backarrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });



    }


    public void findViewsById()

    {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextName=(EditText)findViewById(R.id.editTextName);

        editTextPassword=(EditText)findViewById(R.id.editTextPassword);

        editTextMobile=(EditText)findViewById(R.id.editTextMobile);

        editTextDOB=(EditText)findViewById(R.id.editTextDOB);

        editTextDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddNewFamilyMember.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        editTextRelation=(EditText)findViewById(R.id.editTextRelation);

        editTextRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relationbbottomSheet.title("Select Relation");
                relationbbottomSheet.show();
                relationbbottomSheet.listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       relationModel=relationModelList.get(which);
                        editTextRelation.setText(relationModel.getRelationName());
                    }
                });


            }
        });
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (ApplicationGlobles.isConnectingToInternet(AddNewFamilyMember.this)) {

                    if (checkValidation()) {

                        String URL = Constaints.AddNewFamilyMember;


                        showProgressDialog();
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("Email", editTextEmail.getText().toString().trim());
                        params.put("Name", editTextName.getText().toString().trim());
                        params.put("Password", editTextPassword.getText().toString().trim());
                        params.put("Mobile", editTextMobile.getText().toString().trim());
                        params.put("ConfirmPassword", editTextPassword.getText().toString().trim());
                        params.put("ConfirmPassword", editTextPassword.getText().toString().trim());
                        params.put("RelationId",String.valueOf( relationModel.getRelationID()));
                        processPostRequest(URL, params, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Add New Member", response.toString());



                                hideProgressDialog();



                                try {
                                    JSONObject result = response.getJSONObject("Result");
                                    int status = result.optInt("Status");
                                    if (status == 0) {

                                        JSONArray ArrayErrors=result.getJSONArray("Errors");
                                        Snackbar.make(LayoutView, ArrayErrors.getString(0).toString(), Snackbar.LENGTH_LONG).show();




                                    } else {
                                        //It Means Uploaded Successfully
                                        Snackbar.make(LayoutView, "Member Added Successfully !", Snackbar.LENGTH_LONG).show();
                                        new Handler().postDelayed(new Runnable() {


                                            @Override
                                            public void run() {


                                                finish();


                                            }
                                        }, TIME_OUT);
                                    }


                                } catch (JSONException e) {

                                }


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
        LayoutView = findViewById(R.id.snackbarPosition);
        editTextCity =(EditText) findViewById(R.id.editTextCity);

        editTextCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dFragment.show(fm, "");
            }
        });
        relationbbottomSheet=new BottomSheet.Builder(AddNewFamilyMember.this, R.style.BottomSheet_Dialog);
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

        if (!RegexValiations.hasText(editTextEmail))
            ret = false;

        if (!RegexValiations.hasText(editTextPassword))
            ret = false;
        if (!RegexValiations.hasText(editTextMobile))
            ret = false;
        if (!RegexValiations.hasText(editTextCity))
            ret = false;

        if (!RegexValiations.hasText(editTextName))
            ret = false;
        if (!RegexValiations.hasText(editTextDOB))
            ret = false;
        if (!RegexValiations.hasText(editTextRelation))
            ret = false;
        return ret;
    }


    protected MyJsonRequest processGetRequest(String requestUrl, Map<String, String> params,
                                              Response.Listener<JSONObject> responseListener,
                                              Response.ErrorListener errorListener, String Tag, boolean shouldCache) {
        MyJsonRequest request = new MyJsonRequest(this, Request.Method.GET, requestUrl
                , params, responseListener, errorListener);
        request.setShouldCache(shouldCache);
        addToRequestQueue(request, Tag);
        return request;
    }
    public void loadRelations()
    {
        String URL = Constaints.LoadRelations;




        Map<String, String> params = new HashMap<String, String>();




        processGetRequest(URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Swasthy", response.toString());


                try {


                    JSONArray relations = response.getJSONArray("Items");


                    for (int i = 0; i < relations.length(); i++) {
                        RelationModel model = new RelationModel();
                        JSONObject relation = relations.getJSONObject(i);

                        model.setRelationID(relation.optInt("Key"));
                        model.setRelationName(relation.optString("Value"));
                        relationModelList.add(i, model);


                        relationbbottomSheet.sheet(i, relation.optString("Value"));

                    }


                } catch (JSONException e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();

            }
        }, "Test", false);



    }
}
