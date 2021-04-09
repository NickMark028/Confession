package com.example.confession.models.api;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ApiService {
	
    final String contentType = "application/json; charset=utf-8";
    String apiURL = "https://confessionapi2021.herokuapp.com/";
    String pathURL;
    Context context;
    RequestQueue requestQueue;
    String jsonresponse;

    private Map<String, String> header;

    public ApiService(Context context,String pathURL) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
        header = new HashMap<>();
        header.put("Content-Type", "application/x-www-form-urlencoded");
        this.pathURL = pathURL;
    }

    public void addHeader(String key, String value) {
        header.put(key, value);
    }

    public void executeRequest(int method, final VolleyCallback callback) {
        String cururl = apiURL + pathURL;
        StringRequest stringRequest = new StringRequest(method, cururl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonresponse = response;
                //Log.e("RES", " res::" + jsonresponse)
                try {
                    callback.getResponse(jsonresponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return header;
            }

            /*@Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }*/
		};
		requestQueue.add(stringRequest);
	}
}