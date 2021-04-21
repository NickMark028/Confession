package com.example.confession.models.api;

import org.json.JSONException;

public class VolleyCallback {

	boolean isComplete = false;
	String json;

	public void getResponse(String response) throws JSONException {
		this.isComplete = true;
		this.json = response;
	}
}