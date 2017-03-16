package com.medical.proadoc.Models;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by prateek on 2/27/2016.
 */
public class MedicalServicesProviderModel {


    public int MedicalServicesProviderId;
    public String MedicalServicesProviderName;
    public String MedicalServicesProviderIMage;

    public int getMedicalServicesProviderId() {
        return MedicalServicesProviderId;
    }

    public void setMedicalServicesProviderId(int medicalServicesProviderId) {
        MedicalServicesProviderId = medicalServicesProviderId;
    }

    public String getMedicalServicesProviderName() {
        return MedicalServicesProviderName;
    }

    public void setMedicalServicesProviderName(String medicalServicesProviderName) {
        MedicalServicesProviderName = medicalServicesProviderName;
    }

    public String getMedicalServicesProviderIMage() {
        return MedicalServicesProviderIMage;
    }

    public void setMedicalServicesProviderIMage(String medicalServicesProviderIMage) {
        MedicalServicesProviderIMage = medicalServicesProviderIMage;
    }
    public static ArrayList<MedicalServicesProviderModel> getDummyData(Activity activity) {
        ArrayList<MedicalServicesProviderModel> medicalRecordsArray=new ArrayList<MedicalServicesProviderModel>();



String data=loadJSONFromAsset(activity);
        try {

            JSONObject obj = new JSONObject(data);
            JSONArray m_jArry = obj.getJSONArray("specializations");


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject MedicalServicesProviderObject = m_jArry.getJSONObject(i);

                String MedicalServicesProviderId = MedicalServicesProviderObject.getString("id");
                String MedicalProviderName = MedicalServicesProviderObject.getString("name");

                MedicalServicesProviderModel model=new MedicalServicesProviderModel();
                model.setMedicalServicesProviderName(MedicalProviderName);

                medicalRecordsArray.add(i,model);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }



        return medicalRecordsArray;







    }
    public static String loadJSONFromAsset(Activity activity) {
        String json = null;
        try {
            InputStream is =activity.getAssets().open("specializations.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }



        return json;
    }

    public static  MedicalServicesProviderModel medicalServicesProviderModel;

    public static MedicalServicesProviderModel getMedicalServicesProviderModel() {
        return medicalServicesProviderModel;
    }

    public static void setMedicalServicesProviderModel(MedicalServicesProviderModel medicalServicesProviderModel) {
        MedicalServicesProviderModel.medicalServicesProviderModel = medicalServicesProviderModel;
    }
}
