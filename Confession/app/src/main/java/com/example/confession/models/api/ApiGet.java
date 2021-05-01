package com.example.confession.models.api;

import android.util.Log;

import java.util.HashMap;

public class ApiGet{

	public boolean isComplete = false;
	public String response = "";
	private String url;
	private HashMap<String, String> params;

	public ApiGet(String url, HashMap<String, String> params) {
		this.url = url;
		this.params = params;
	}

	public String run() {
		ApiController ac = new ApiController();
		this.response = ac.get(this.url, this.params);
		return response;
	}
}
