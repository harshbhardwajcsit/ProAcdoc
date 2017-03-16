package com.medical.proadoc.HelperClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

@SuppressLint("WorldWriteableFiles")
public class SessionManager {

	private static String UID = "UID";


	private static String FullName="FullName";

	private static String EmailAddress = "EmailAddress";


	private static String RefererCode="RefererCode";
	private static String PhoneNumber="PhoneNumber";
	private static String Image="Image";

	private static String Designation="Designation";
	private static String WorkPlace="WorkPlace";
	private static String CountryId="CountryId";
    private static String Role="Role";
	private static String AuthToken="AuthToken";













	public static String getAuthToken(Context context) {

		return getPrefs(context).getString(AuthToken, AuthToken);
	}



	public static void setAuthToken(Context context, String value) {

		getPrefs(context).edit().putString(AuthToken, value).commit();
	}














    public static String getRole(Context context) {

        return getPrefs(context).getString(Role, Role);
    }



    public static void setRole(Context context, String value) {

        getPrefs(context).edit().putString(Role, value).commit();
    }







	public static String getCountryId(Context context) {

		return getPrefs(context).getString(CountryId, CountryId);
	}



	public static void setCountryId(Context context, String value) {

		getPrefs(context).edit().putString(CountryId, value).commit();
	}
















	public static String getWorkPlace(Context context) {

		return getPrefs(context).getString(WorkPlace, WorkPlace);
	}



	public static void setWorkPlace(Context context, String value) {

		getPrefs(context).edit().putString(WorkPlace, value).commit();
	}



















	public static String getDesignation(Context context) {

		return getPrefs(context).getString(Designation, Designation);
	}



	public static void setDesignation(Context context, String value) {

		getPrefs(context).edit().putString(Designation, value).commit();
	}









	public static String getImage(Context context) {

		return getPrefs(context).getString(Image, Image);
	}



	public static void setImage(Context context, String value) {

		getPrefs(context).edit().putString(Image, value).commit();
	}















	public static String getPhoneNumber(Context context) {

		return getPrefs(context).getString(PhoneNumber, PhoneNumber);
	}



	public static void setPhoneNumber(Context context, String value) {

		getPrefs(context).edit().putString(PhoneNumber, value).commit();
	}
















	public static SharedPreferences getPrefs(Context context) {

		return context.getSharedPreferences("UserNameAcrossApplication",
				context.MODE_WORLD_WRITEABLE);

	}
	
	
	

	public static String getEmailAddress(Context context) {

		return getPrefs(context).getString(EmailAddress, EmailAddress);
	}



	public static void setEmailAddress(Context context, String value) {

		getPrefs(context).edit().putString(EmailAddress, value).commit();
	}

	
	
	
	

	
	

	public static String getUID(Context context) {

		return getPrefs(context).getString(UID, UID);
	}

	

	public static void setUID(Context context, String value) {

		getPrefs(context).edit().putString(UID, value).commit();
	}



	public static String getFullName(Context context) {

		return getPrefs(context).getString(FullName, FullName);
	}



	public static void setFullName(Context context, String value) {

		getPrefs(context).edit().putString(FullName, value).commit();
	}




	public static String getPromoCode(Context context) {

		return getPrefs(context).getString(RefererCode, RefererCode);
	}



	public static void setRefererCode(Context context, String value) {

		getPrefs(context).edit().putString(RefererCode, value).commit();
	}

	
	
	
	
	
	
	
}