package com.medical.proadoc;


        import android.content.Intent;
        import android.os.Build;
        import android.os.Bundle;
        import android.provider.Settings;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.View;
        import android.view.Window;
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
        import com.medical.proadoc.Volley.MyJsonRequest;


        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.Map;

public class ForgotPassword extends AppCompatActivity {


    public TextView TextViewHeading;
    public ProgressBar progressBar;
    private View LayoutView;

    public EditText editTextPassword;
    private String TAG = Login.class.getSimpleName();

    private static RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_forgot_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView   mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Recover Password");

        findViewsById();

    }
    public void RecoverPassword(View v)
    {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        if(  ApplicationGlobles.isConnectingToInternet(ForgotPassword.this)) {





            if (checkValidation()) {


                if (ApplicationGlobles.isConnectingToInternet(ForgotPassword.this)) {

                    if (checkValidation()) {

                        String URL = Constaints.LoginUrl;


                        showProgressDialog();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("grant_type", "password");
                        params.put("client_id", "SwasthyAndroidApp");
                        params.put("username", editTextPassword.getText().toString().trim());



                        processPostRequest(URL, params, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Swasthy", response.toString());
                                Toast.makeText(getBaseContext(), "go" + response.toString(), Toast.LENGTH_LONG).show();
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
        }
        else
        {
            Snackbar.make(LayoutView,getResources().getString(R.string.InternetError) , Snackbar.LENGTH_LONG).setAction("Check Settings", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(
                            new Intent(Settings.ACTION_SETTINGS));
                }
            }).show();

        }
    }


    private void findViewsById() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.username,0,0,0);
        editTextPassword.setTypeface(ApplicationGlobles.loadMyriadProFonts(getApplicationContext()));
        LayoutView = findViewById(R.id.snackbarPosition);




    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!RegexValiations.isEmailAddress(editTextPassword, true))
            ret = false;



        return ret;
    }

    public void login_click(View v)
    {

        Intent i=new Intent(ForgotPassword.this,Login.class);
        startActivity(i);
    }
    public void register_click(View v)
    {

        Intent i=new Intent(ForgotPassword.this,Register.class);
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
