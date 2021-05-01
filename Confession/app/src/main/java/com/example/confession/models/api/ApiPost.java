package com.example.confession.models.api;

import java.util.HashMap;

public class ApiPost{

	public boolean isComplete = false;
	public String response;
	private String url;
	private HashMap<String, String> params;

	public ApiPost(String url, HashMap<String, String> params) {
		this.url = url;
		this.params = params;
	}


	public String run() {
		ApiController ac = new ApiController();
		this.response = ac.post(this.url, this.params);
		return this.response;
	}
}
