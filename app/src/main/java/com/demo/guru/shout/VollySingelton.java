package com.demo.guru.shout;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Objects;

/**
 * Created by navjot on 10/25/2015.
 */
public class VollySingelton extends Application{

    public static final String TAG = VollySingelton.class.getSimpleName();

    private  RequestQueue mRequestQueue;

    private static  VollySingelton mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static synchronized VollySingelton getmInstance()
    {
        return mInstance;
    }

    public RequestQueue getmRequestQueue()
    {
        if(mRequestQueue==null)
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req,String tag)
    {
        req.setTag(TextUtils.isEmpty(tag)? TAG : tag);
        getmRequestQueue().add(req);

    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        req.setTag(TAG);
        getmRequestQueue().add(req);

    }

    public  void cancelPendingRequests(Objects tag){

        if(mRequestQueue!=null)
        {
            mRequestQueue.cancelAll(tag);
        }

    }


}
