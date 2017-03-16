package com.medical.proadoc.HelperClasses;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("DefaultLocale")
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class ApplicationGlobles {
    public final static int CAMERA_REQUEST_CODE = 2222;
    public final static int MemCard_REQUEST_CODE = 222;
    public final static int Zingbarcode_REQUEST_CODE = 786;
    public final static int CropImage_REQUEST_CODE = 209;
    public static String NoReadingError = "No Reading Available";
    public static NotificationManager mNotificationManager;

    public static boolean isNullOrEmpty(String myString) {
        return myString == null || "".equals(myString);
    }

    public static void loadStrictModePolicies() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public static boolean isConnectingToInternet(Activity CurrentActivity) {
        Boolean Connected = false;
        ConnectivityManager connectivity = (ConnectivityManager) CurrentActivity
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        Log.e("Network is: ", "Connected");
                        Connected = true;
                    } else {
                    }

        } else {
            Log.e("Network is: ", "Not Connected");

            Toast.makeText(CurrentActivity.getApplicationContext(),
                    "Please Check Your  internet connection", Toast.LENGTH_LONG)
                    .show();
            Connected = false;

        }
        return Connected;

    }

    public static boolean compareString(String stringOne, String stringTwo) {
        Boolean compareStatus = false;
        String fooString1 = new String(stringOne);
        String fooString2 = new String(stringTwo);

        if (fooString1.equals(fooString2)) {
            compareStatus = true;
        } else {
            compareStatus = false;

        }
        return compareStatus;
    }

    public static Typeface loadBookOS(Context _context) {

        Typeface BOOKOS = Typeface.createFromAsset(_context.getAssets(),
                "BOOKOS.TTF");
        return BOOKOS;

    }

    public static Typeface loadLatoFonts(Context _context) {

        Typeface Lato = Typeface.createFromAsset(_context.getAssets(),
                "Lato-Regular.ttf");
        return Lato;

    }

    public static Typeface loadHelveticaNeueFonts(Context _context) {

        Typeface HelveticaNeue = Typeface.createFromAsset(_context.getAssets(),
                "HelveticaNeue.ttf");
        return HelveticaNeue;

    }

    public static Typeface loadMyriadProFonts(Context _context) {

        Typeface Myriad_Pro = Typeface.createFromAsset(_context.getAssets(),
                "Myriad_Pro.ttf");
        return Myriad_Pro;

    }


    @SuppressLint("SimpleDateFormat")
    public static String getTodaysDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String TodaysDate = sdf.format(new Date());
        return TodaysDate;

    }

    public static String BitmapTobase64(Bitmap photo) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }

    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static String convertMiliSecondToFormatedElapsedTime(long millis) {

        String hms = String.format(
                "%02d:%02d",

                TimeUnit.MILLISECONDS.toMinutes(millis)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                        .toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                        .toMinutes(millis)));

        return hms;

    }

    public static String getFirst10Words(String arg) {
        Pattern pattern = Pattern.compile("([\\S]+\\s*){1,5}");
        Matcher matcher = pattern.matcher(arg);
        matcher.find();
        return matcher.group();
    }

    public static void ShowKeyBoard(Activity activity, EditText edittext) {

        InputMethodManager inputMethodManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // only will trigger it if no physical keyboard is open
        inputMethodManager.showSoftInput(edittext,
                InputMethodManager.SHOW_IMPLICIT);

    }

    public static void hideKeyboard(Activity activity) {

        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }


    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

    public static String getdateFromString(String string) {

        String date = string;
        String arr[] = date.split("/");


        String Day = arr[1];

        String Month = arr[0];
        String Year = arr[2];

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();


        string = Month + "/" + Day + "/" + Year;


        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date date1 = new Date();
        String dayOfTheWeek = Day + "/" + Month + "-" + Year + "-" + sdf.format(date1.parse(string));
        return dayOfTheWeek;


    }

    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

}
