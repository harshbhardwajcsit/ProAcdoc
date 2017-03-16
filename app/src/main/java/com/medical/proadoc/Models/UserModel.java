package com.medical.proadoc.Models;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.util.List;
import java.util.Locale;

/**
 * Created by intel on 10/7/2015.
 */
public class UserModel {

    public static String TokenNo = "taflJ0GMbvgVe3m2b7ryDQ";

    public String City;
    public String State;

    public String Email;

    public static String getTokenNo() {
        return TokenNo;
    }

    public static void setTokenNo(String tokenNo) {
        TokenNo = tokenNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String Password;
    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String Country;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String Image;

    public String Name;
    public String Mobile;
    public String UserEncryptedID;

    public String TotalReviews;

    public String TotalFollowers;

    public String getTotalReviews() {
        return TotalReviews;
    }

    public void setTotalReviews(String totalReviews) {
        TotalReviews = totalReviews;
    }

    public String getTotalFollowers() {
        return TotalFollowers;
    }

    public void setTotalFollowers(String totalFollowers) {
        TotalFollowers = totalFollowers;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getUserEncryptedID() {
        return UserEncryptedID;
    }

    public void setUserEncryptedID(String userEncryptedID) {
        UserEncryptedID = userEncryptedID;
    }
    public static String getCompleteAddressString(Double LATITUDE,
                                                  Double LONGITUDE, Context context) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE,
                    LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress
                            .append(returnedAddress.getAddressLine(i)).append(
                            "\n");
                }
                strAdd = strReturnedAddress.toString();

            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return strAdd;
    }
    public static Transaction UpdateRegistrationID(Context context,String DeviceID) {
        Transaction a=new Transaction();
        return a;

    }


}
