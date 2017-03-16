package com.medical.proadoc.Volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;

/**
 * Created by Clvoer on 10/30/2015.
 */
public abstract class BaseObjectRequest<T> extends Request<T> {

    protected Response.Listener<T> mListener;
    protected Map<String, String> params;
    Context mContext;

    public BaseObjectRequest(Context context,String url, Map<String, String> params,
                             Response.Listener<T> reponseListener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.mListener = reponseListener;
        this.params = params;
        mContext = context;
    }

    public BaseObjectRequest(Context context,int method, String url, Map<String, String> params,
                             Response.Listener<T> reponseListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = reponseListener;
        this.params = params;
        mContext = context;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

}
