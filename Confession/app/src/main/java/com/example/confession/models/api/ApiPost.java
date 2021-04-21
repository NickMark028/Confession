package com.example.confession.models.api;

import java.util.HashMap;

public class ApiPost implements Runnable {

	public boolean isComplete = false;
	public String response;
	private String url;
	private HashMap<String, String> params;

	public ApiPost(String url, HashMap<String, String> params) {
		this.url = url;
		this.params = params;
	}

	@Override
	public void run() {
		ApiController ac = new ApiController();
		this.response = ac.post(this.url, this.params);
		this.isComplete = true;
	}
}
