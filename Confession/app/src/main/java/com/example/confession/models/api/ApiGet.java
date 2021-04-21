package com.example.confession.models.api;

import android.util.Log;

import java.util.HashMap;

public class ApiGet implements Runnable{
    public boolean isComplete = false;
    public String response="";
    private String url;
    private HashMap<String,String> params;

    public ApiGet(String url, HashMap<String,String> params)
    {
        this.url = url;
        this.params = params;
    }

    @Override
    public void run() {
        ApiController ac = new ApiController();
        this.response=ac.get(this.url,this.params);
        Log.e("IsComplete: ",new  Boolean(this.isComplete).toString());
        this.isComplete=true;
        Log.e("IsCompleteAfterSet: ",new  Boolean(this.isComplete).toString());
    }
}