package com.medical.proadoc;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.medical.proadoc.HelperClasses.SessionManager;
import com.medical.proadoc.Models.BodyPartModel;
import com.medical.proadoc.Models.FamilyMembersModel;
import com.medical.proadoc.Volley.MyJsonRequest;
import com.medical.proadoc.Volley.UploadMultiPartVolley.MyJsonRequestUplaod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by hp on 2/8/2016.
 */
public class AddMedicalReport extends Activity {
    public LinearLayout lvUpload;
    public ProgressBar progressBar;
    private View LayoutView;

    private String TAG = AddMedicalReport.class.getSimpleName();


    public EditText editTextSelectMember,editTextBodyPart;
FloatingActionButton floatingActionButton;
    AlertDialog.Builder builder;

    private static RequestQueue mRequestQueue;

    public static BodyPartModel bodyPartModel;
    public static FamilyMembersModel familyMembersModel;
    private ArrayList<BodyPartModel> bodyPartList=new ArrayList<BodyPartModel>(0);
    private ArrayList<FamilyMembersModel> familyMembersList=new ArrayList<FamilyMembersModel>(0);
    BottomSheet.Builder bodyPartbbottomSheet;
    BottomSheet.Builder familyMembersbottomSheet;
    private static int TIME_OUT = 2000;


    public ImageView imageButton;

    private Uri fileUri;
    String picturePath;
    Uri selectedImage;
    Bitmap photo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_addmedicalreport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("");

        toolbar.inflateMenu(R.menu.menu_main);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.backarrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        FindViewsById();
        loadMembers();
        loadBodyParts();


    }


    public void FindViewsById()
    {
        familyMembersbottomSheet=new BottomSheet.Builder(AddMedicalReport.this, R.style.BottomSheet_Dialog);
        bodyPartbbottomSheet=new BottomSheet.Builder(AddMedicalReport.this, R.style.BottomSheet_Dialog);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageButton = (ImageView) findViewById(R.id.imageButton);
        LayoutView = findViewById(R.id.snackbarPosition);

        editTextSelectMember=(EditText)findViewById(R.id.editTextSelectMember);

        editTextSelectMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                familyMembersbottomSheet.title("Select Member");
                familyMembersbottomSheet.show();
                familyMembersbottomSheet.listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        familyMembersModel = familyMembersList.get(which);
                        editTextSelectMember.setText(familyMembersModel.getName());

                    }
                });
            }
        });







        editTextBodyPart=(EditText)findViewById(R.id.editTextBodyPart);
        editTextBodyPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bodyPartbbottomSheet.title("Select Body Part");
                bodyPartbbottomSheet.show();
                bodyPartbbottomSheet.listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bodyPartModel = bodyPartList.get(which);
                        editTextBodyPart.setText(bodyPartModel.getBodyPartName());

                    }
                });
            }
        });


        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ApplicationGlobles.isConnectingToInternet(AddMedicalReport.this)) {

                    if (checkValidation()) {

                        String URL = Constaints.AddNewReport;


                        showProgressDialog();
                        Map<String, String> params = new HashMap<String, String>();
                        ByteArrayOutputStream byteStream = null;
                        byteStream = new ByteArrayOutputStream();
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, byteStream);
                        byte[] byteArray = byteStream.toByteArray();
                        params.put("image", byteArray.toString());
                        params.put("UserId", String.valueOf(familyMembersModel.getMemberId()));
                        params.put("BodyPartId", String.valueOf(bodyPartModel.getBodyPartId()));

                        try {
                            put(URL);
                        } catch (Exception e) {

                        }

                        processPostRequest(URL, params, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Swasthy", response.toString());
                                //  Toast.makeText(getBaseContext(), "go" + response.toString(), Toast.LENGTH_LONG).show();


                                hideProgressDialog();
                                try {
                                    JSONObject result = response.getJSONObject("Result");
                                    int status = result.optInt("Status");
                                    if (status == 0) {

                                        JSONArray ArrayErrors = result.getJSONArray("Errors");
                                        Snackbar.make(LayoutView, ArrayErrors.getString(0).toString(), Snackbar.LENGTH_LONG).show();


                                    } else {
                                        //It Means Uploaded Successfully
                                        Snackbar.make(LayoutView, "Uploaded To Cloud Successfully !", Snackbar.LENGTH_LONG).show();
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
        lvUpload=(LinearLayout)findViewById(R.id.lvUpload);
        lvUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                builder =
                        new AlertDialog.Builder(AddMedicalReport.this, R.style.AppCompatAlertDialogStyle);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.customimagepickerlayout, null);
                builder.setView(dialogView);

                builder.setNegativeButton("Cancel", null);
                builder.setTitle("Upload Reports Or Prescriptions");


                builder.show();


                Button buttonCamera = (Button) dialogView.findViewById(R.id.buttonCamera);
                buttonCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickpic();
                    }
                });


                Button buttonMemory = (Button) dialogView.findViewById(R.id.buttonMemory);
                buttonMemory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EasyImage.openGallery(AddMedicalReport.this, EasyImage.REQ_PICK_PICTURE_FROM_GALLERY);
                    }
                });
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {

            selectedImage = data.getData();
            photo = (Bitmap) data.getExtras().get("data");

            // Cursor to get image uri to display

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

          imageButton.setImageBitmap(photo);
        }
    }
    private void clickpic() {
        // Check Camera
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // Open default camera
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            // start the image capture Intent
            startActivityForResult(intent, 100);

        } else {
            Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!RegexValiations.hasText(editTextBodyPart))
            ret = false;

        if (!RegexValiations.hasText(editTextSelectMember))
            ret = false;



        return ret;
    }
    private void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);

    }

    private void hideProgressDialog() {
        progressBar.setVisibility(View.GONE);
    }

    protected MyJsonRequestUplaod processPostRequest(String requestUrl, Map<String, String> params,
                                               Response.Listener<JSONObject> responseListener,
                                               Response.ErrorListener errorListener, String Tag, boolean shouldCache) {
        MyJsonRequestUplaod request = new MyJsonRequestUplaod(this, Request.Method.POST, requestUrl
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


    protected MyJsonRequest processGetRequest(String requestUrl, Map<String, String> params,
                                              Response.Listener<JSONObject> responseListener,
                                              Response.ErrorListener errorListener, String Tag, boolean shouldCache) {
        MyJsonRequest request = new MyJsonRequest(this, Request.Method.GET, requestUrl
                , params, responseListener, errorListener);
        request.setShouldCache(shouldCache);
        addToRequestQueue(request, Tag);
        return request;
    }
    public void loadBodyParts()
    {
        String URL = Constaints.LoadBodyParts;




        Map<String, String> params = new HashMap<String, String>();




        processGetRequest(URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Swasthy", response.toString());
                // Toast.makeText(getApplicationContext(), "go" + response.toString(), Toast.LENGTH_LONG).show();

                try {


                    JSONArray relations = response.getJSONArray("Items");


                    for (int i = 0; i < relations.length(); i++) {
                        BodyPartModel model = new BodyPartModel();
                        JSONObject bodypart = relations.getJSONObject(i);

                        model.setBodyPartId(bodypart.optInt("Key"));
                        model.setBodyPartName(bodypart.optString("Value"));


                        bodyPartList.add(i, model);


                        bodyPartbbottomSheet.sheet(i, bodypart.optString("Value"));

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



    public void loadMembers()
    {
        String URL = Constaints.LoadFamilyMembers;




        Map<String, String> params = new HashMap<String, String>();




        processGetRequest(URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Swasthy", response.toString());
              //  Toast.makeText(getApplicationContext(), "go" + response.toString(), Toast.LENGTH_LONG).show();

                try {


                    JSONArray familyMembersArray = response.getJSONArray("Items");


                    for (int i = 0; i < familyMembersArray.length(); i++) {
                        FamilyMembersModel model = new FamilyMembersModel();
                        JSONObject familyMember = familyMembersArray.getJSONObject(i);

                        model.setMemberId(familyMember.optInt("Key"));
                        model.setName(familyMember.optString("Value"));


                        familyMembersList.add(i, model);


                        familyMembersbottomSheet.sheet(i, familyMember.optString("Value"));

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

    public  void put(String targetURL) throws Exception {

        String BOUNDRY = "==================================";
        HttpURLConnection conn = null;

        try {
            File file=new File("a.jpg");

            // These strings are sent in the request body. They provide information about the file being uploaded
            String contentDisposition = "Content-Disposition: form-data; name=\"userfile\"; filename=\"" + file.getName() + "\"";
            String contentType = "Content-Type: application/octet-stream";

            // This is the standard format for a multipart request
            StringBuffer requestBody = new StringBuffer();
            requestBody.append("--");
            requestBody.append(BOUNDRY);
            requestBody.append('\n');
            requestBody.append(contentDisposition);
            requestBody.append('\n');
            requestBody.append(contentType);
            requestBody.append('\n');
            requestBody.append('\n');

            requestBody.append(new String(ApplicationGlobles.BitmapTobase64(photo)));
            requestBody.append("--");
            requestBody.append(BOUNDRY);
            requestBody.append("--");

            // Make a connect to the server
            URL url = new URL(targetURL);
            conn = (HttpURLConnection) url.openConnection();

            // Put the authentication details in the request
            conn.setRequestProperty ("Authorization", "Bearer " + SessionManager.getAuthToken(getApplicationContext()));

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDRY);

            // Send the body
            DataOutputStream dataOS = new DataOutputStream(conn.getOutputStream());
            dataOS.writeBytes(requestBody.toString());
            dataOS.flush();
            dataOS.close();

            // Ensure we got the HTTP 200 response code
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new Exception(String.format("Received the response code %d from the URL %s", responseCode, url));
            }

            // Read the response
            InputStream is = conn.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int bytesRead;
            while((bytesRead = is.read(bytes)) != -1) {
                baos.write(bytes, 0, bytesRead);
            }
            byte[] bytesReceived = baos.toByteArray();
            baos.close();

            is.close();
            String response = new String(bytesReceived);

            // TODO: Do something here to handle the 'response' string

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

    }

}
