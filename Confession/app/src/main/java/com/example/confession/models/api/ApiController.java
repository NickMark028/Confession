package com.example.confession.models.api;

import android.media.effect.EffectUpdateListener;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class ApiController {

	final String apiURL = "https://confessionapi2021.herokuapp.com/";

	private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {

	    StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
		}
		return result.toString();
	}

	public String post(String path, HashMap<String, String> postDataParams) {

		String requestURL = this.apiURL + path;
		URL url;
		String response = "";
		try {
			url = new URL(requestURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(15000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(getPostDataString(postDataParams));
			writer.flush();
			writer.close();
			os.close();
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpsURLConnection.HTTP_OK) {
				String line;
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = br.readLine()) != null) {
					response += line;
				}
			} else {
				response = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public String get(String path, HashMap<String, String> query) {
		String response = "";
		String fullurl = this.apiURL + path;
		int i = 1;
		for (Map.Entry<String, String> item : query.entrySet()) {
			if (i == 1) {
				Log.d("1111111", "");
				fullurl = fullurl + "?" + item.getKey() + "=" + item.getValue();
			} else {
				fullurl = fullurl + "&" + item.getKey() + "=" + item.getValue();
			}
			i++;

		}
		Log.d("fullurl", fullurl);

		URL url = null;
		try {
			url = new URL(fullurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(15000);
			conn.setConnectTimeout(15000);

			int responseCode = conn.getResponseCode();
			if (responseCode == HttpsURLConnection.HTTP_OK) {
				String line;
				BufferedReader buf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = buf.readLine()) != null) {
					response += line;
					Log.e("Line", line);
				}
				buf.close();
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			Log.e("Line", "AA");
		} catch (IOException e) {
			Log.e("Line", "AA");
		}
		return response;
	}
}
