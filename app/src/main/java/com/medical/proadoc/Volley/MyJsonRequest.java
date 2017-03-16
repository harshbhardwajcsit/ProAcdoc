package com.medical.proadoc.Volley;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.medical.proadoc.HelperClasses.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Clvoer on 11/4/2015.
 */
public class MyJsonRequest extends BaseObjectRequest<JSONObject> {
    public Context _context;

    public MyJsonRequest(Context context, String url, Map<String, String> params, Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener) {
        super(context,url, params, reponseListener, errorListener);
        _context=context;
    }

    public MyJsonRequest(Context context, int method, String url, Map<String, String> params, Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener) {
        super(context,method, url, params, reponseListener, errorListener);
        _context=context;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            //String jsonString =
            //      new String(response.data, MyHttpHeaderParser.parseCharset(response.headers));
            String jsonString = new String(response.data);
            /*response.headers.put("Authorization",
                   "Bearer "+ SessionManager.getAuthToken(_context));
            Log.e("Authorization",  "Bearer "+ SessionManager.getAuthToken(_context));*/
            return Response.success(new JSONObject(jsonString),
                    MyHttpHeaderParser.parseIgnoreCacheHeaders(response));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> head = new HashMap<String, String>();


        head.put(
                "Authorization",
                "Bearer "+SessionManager.getAuthToken(_context));




        Log.e("Authorization", "Bearer " + SessionManager.getAuthToken(_context));


        return head;
    }



}
