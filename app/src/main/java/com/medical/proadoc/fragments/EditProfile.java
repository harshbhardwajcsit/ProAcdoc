package com.medical.proadoc.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.cocosw.bottomsheet.BottomSheet;
import com.medical.proadoc.HelperClasses.ApplicationGlobles;
import com.medical.proadoc.HelperClasses.Constaints;
import com.medical.proadoc.HelperClasses.RegexValiations;
import com.medical.proadoc.Home;
import com.medical.proadoc.Models.BloodGroup;
import com.medical.proadoc.Models.RelationModel;
import com.medical.proadoc.R;
import com.medical.proadoc.Volley.MyJsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by hp on 2/8/2016.
 */
public class EditProfile extends Fragment {

    public EditText editTextName,editTextContactNo,editTextPassword,editTextBloodGroup;
    public ProgressBar progressBar;
    private View LayoutView;

    private String TAG = "EditProfile";
    FloatingActionButton floatingActionButton;
    private static RequestQueue mRequestQueue;
    public ImageView ImageViewUser;
    AlertDialog.Builder builder;
    private ArrayList<BloodGroup> bloodbroupModelList=new ArrayList<BloodGroup>(0);
    BottomSheet.Builder bloodgroupModelbbottomSheet;
    public static  BloodGroup bloodgroupModel;


    public EditProfile() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editprofile, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        findViewById();
        bloodgroupModelbbottomSheet=new BottomSheet.Builder(getActivity(), R.style.BottomSheet_Dialog);
        loadBloodGroups();
        super.onActivityCreated(savedInstanceState);
    }

    public void findViewById()
    {

        editTextName=(EditText)getView().findViewById(R.id.editTextName);
                editTextContactNo=(EditText)getView().findViewById(R.id.editTextContactNo);
        editTextPassword=(EditText)getView().findViewById(R.id.editTextPassword);
                editTextBloodGroup=(EditText)getView().findViewById(R.id.editTextBloodGroup);
        editTextBloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodgroupModelbbottomSheet.title("Select Blood Group");
                bloodgroupModelbbottomSheet.show();
                bloodgroupModelbbottomSheet.listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bloodgroupModel=bloodbroupModelList.get(which);
                        editTextBloodGroup.setText(bloodgroupModel.getBloodGroupName());
                    }
                });
            }
        });

        progressBar = (ProgressBar)getView(). findViewById(R.id.progressBar);
        LayoutView = getView().findViewById(R.id.snackbarPosition);
        floatingActionButton=(FloatingActionButton)getView().findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ApplicationGlobles.isConnectingToInternet(getActivity())) {

                    if (checkValidation()) {

                        String URL = Constaints.LoginUrl;


                        showProgressDialog();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("grant_type", "password");
                        params.put("client_id", "SwasthyAndroidApp");
                        params.put("username", editTextBloodGroup.getText().toString().trim());
                        params.put("password", editTextContactNo.getText().toString().trim());
                        params.put("username", editTextName.getText().toString().trim());
                        params.put("password", editTextPassword.getText().toString().trim());

                        processPostRequest(URL, params, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Swasthy", response.toString());
                                Toast.makeText(getActivity().getApplicationContext(), "go" + response.toString(), Toast.LENGTH_LONG).show();


                                hideProgressDialog();


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_LONG).show();
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

        ImageViewUser=(ImageView)getView().findViewById(R.id.ImageViewUser);
        ImageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder =
                        new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
                LayoutInflater inflater =getActivity(). getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.customimagepickerlayout, null);
                builder.setView(dialogView);

                builder.setNegativeButton("Cancel", null);
                builder.setTitle("Upload Reports Or Prescriptions");


                builder.show();


                Button buttonCamera = (Button) dialogView.findViewById(R.id.buttonCamera);
                buttonCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EasyImage.openCamera(EditProfile.this, EasyImage.REQ_TAKE_PICTURE);
                    }
                });


                Button buttonMemory = (Button) dialogView.findViewById(R.id.buttonMemory);
                buttonMemory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EasyImage.openGallery(EditProfile.this, EasyImage.REQ_PICK_PICTURE_FROM_GALLERY);
                    }
                });
            }
        });


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

    private void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);

    }

    private void hideProgressDialog() {
        progressBar.setVisibility(View.GONE);
    }

    protected MyJsonRequest processPostRequest(String requestUrl, Map<String, String> params,
                                               Response.Listener<JSONObject> responseListener,
                                               Response.ErrorListener errorListener, String Tag, boolean shouldCache) {
        MyJsonRequest request = new MyJsonRequest(getActivity(), Request.Method.POST, requestUrl
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
            mRequestQueue = Volley.newRequestQueue(getActivity());
        }
        return mRequestQueue;
    }
    private boolean checkValidation() {
        boolean ret = true;

        if (!RegexValiations.hasText(editTextName))
            ret = false;

        if (!RegexValiations.hasText(editTextContactNo))
            ret = false;

        if (!RegexValiations.hasText(editTextBloodGroup))
            ret = false;
        if (!RegexValiations.hasText(editTextPassword))
            ret = false;

        return ret;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                //Handle the image
                // onPhotoReturned(imageFile);
            }
        });
    }
    protected MyJsonRequest processGetRequest(String requestUrl, Map<String, String> params,
                                              Response.Listener<JSONObject> responseListener,
                                              Response.ErrorListener errorListener, String Tag, boolean shouldCache) {
        MyJsonRequest request = new MyJsonRequest(getActivity(), Request.Method.GET, requestUrl
                , params, responseListener, errorListener);
        request.setShouldCache(shouldCache);
        addToRequestQueue(request, Tag);
        return request;
    }
    public void loadBloodGroups()
    {
        String URL = Constaints.LoadBloodGroup;




        Map<String, String> params = new HashMap<String, String>();




        processGetRequest(URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Swasthy", response.toString());
               // Toast.makeText(getActivity().getApplicationContext(), "go" + response.toString(), Toast.LENGTH_LONG).show();

                try {


                    JSONArray relations = response.getJSONArray("Items");


                    for (int i = 0; i < relations.length(); i++) {
                        BloodGroup model = new BloodGroup();
                        JSONObject relation = relations.getJSONObject(i);

                        model.setBloodGroupID(relation.optInt("Key"));
                        model.setBloodGroupName(relation.optString("Value"));
                        bloodbroupModelList.add(i, model);


                        bloodgroupModelbbottomSheet.sheet(i, relation.optString("Value"));

                    }


                } catch (JSONException e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_LONG).show();

            }
        }, "Test", false);



    }
}
